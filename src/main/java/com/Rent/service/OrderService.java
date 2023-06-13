package com.Rent.service;

import com.Rent.dto.BookingDto;
import com.Rent.dto.OrderTableDto;
import com.Rent.models.Booking;
import com.Rent.models.Order_Table;
import com.Rent.repository.OrderRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void generateOrder(Booking booking) throws Exception {
        BigDecimal pricePerDay = booking.getProduct().getPriceperday();
        BigDecimal securityDeposit = booking.getProduct().getSecurityDeposite();
        BigDecimal serviceCharge = new BigDecimal("10");
        System.out.println(pricePerDay);
        System.out.println(securityDeposit);
        System.out.println(serviceCharge);
        int numOfDays = (int) ChronoUnit.DAYS.between(booking.getStartDate().toInstant(), booking.getEndDate().toInstant()) + 1;
        System.out.println(numOfDays);
        BigDecimal total = pricePerDay.multiply(BigDecimal.valueOf(numOfDays))
                .add(securityDeposit).add(pricePerDay.multiply(BigDecimal.valueOf(numOfDays)).multiply(serviceCharge.divide(BigDecimal.valueOf(100))));

        Order_Table orderTable = new Order_Table();
        orderTable.setBooking(booking);
        orderTable.setNumOfDays(numOfDays);
    //    orderTable.setPricePerDay(pricePerDay);
     //   orderTable.setSecurityDeposit(securityDeposit);
        orderTable.setServiceCharge(serviceCharge);
        orderTable.setTotal(total);
      //  orderTable.setOwnerName(booking.getUser().getUserName());
       // orderTable.set
      //  orderTable.setProductName(booking.getProduct().getPname());
        booking.setOrderTable(orderTable);

       // System.out.println("yha tak first class");
        try {

            String folderPath = new ClassPathResource("static/BILLS/").getFile().getAbsolutePath();
            String fileName = "bill_" + booking.getId() + ".pdf";
            String filePath = folderPath + fileName;

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Document document = new Document();
            //    PdfWriter.getInstance(document, baos);
            String RenterName= orderTable.getBooking().getUser().getUserName();
            String OwnerName=  orderTable.getBooking().getProduct().getUser().getUserName() ;
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            document.add(new Paragraph("Order Details"));
            document.add(new Paragraph("Product: " + orderTable.getBooking().getProduct().getPname()));
            document.add(new Paragraph("Renter: " + RenterName));
            document.add(new Paragraph("Owner: " + OwnerName));
            document.add(new Paragraph("Start Date: " + orderTable.getBooking().getStartDate()));
            document.add(new Paragraph("End Date: " + orderTable.getBooking().getEndDate()));
            document.add(new Paragraph("Price Per Day: " + orderTable.getBooking().getProduct().getPriceperday()));
            document.add(new Paragraph("Security Deposit: " + orderTable.getBooking().getProduct().getSecurityDeposite()));
            document.add(new Paragraph("Service Charge " + orderTable.getServiceCharge()));
            document.add(new Paragraph("Total Days " + orderTable.getNumOfDays()));
            document.add(new Paragraph("Total Price: " + orderTable.getTotal()));

            document.close();

            writer.close();
            File file = new File(filePath);
            byte[] data = Files.readAllBytes(file.toPath());
            orderTable.setPdfname(data);
            orderRepository.save(orderTable);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }



        public BookingDto convertToBookingDto(Booking booking) {


            Calendar calendar = Calendar.getInstance();
            calendar.setTime(booking.getEndDate()); // Set the date value you want to crop time from
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            Date croppedEDate = calendar.getTime();
            calendar.setTime(booking.getStartDate()); // Set the date value you want to crop time from
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            Date croppedSDate = calendar.getTime();



          //  System.out.println("Cropped"+croppedEDate);
            String end= croppedEDate.toString();
            System.out.println("end is"+end);
            String sta=croppedSDate.toString();
            String []w= end.split("\\s");
            String []Q= sta.split("\\s");
            String fed="";
            String sed="";
            for(int i=1;i<3;i++)
            {
                sed= sed+ Q[i]+" ";

            }

           sed= sed+ w[w.length-1];
            for(int i=1;i<3;i++)
            {
                fed= fed+ w[i]+" ";

            }
            fed= fed+ w[w.length-1];
         //   System.out.println("fed is"+ fed);
          //  String ed= end.substring(0,3);

            BookingDto bookingDto = new BookingDto();
            bookingDto.setBid(booking.getId());
            bookingDto.setProductId(booking.getProduct().getId());
            bookingDto.setEndDate(fed);
            bookingDto.setStartDate(sed);
            bookingDto.setOwnerName(booking.getProduct().getUser().getUserName());
            bookingDto.setRenterName(booking.getUser().getUserName());
            bookingDto.setQuantity(booking.getQuantity());
            bookingDto.setStatus(booking.getStatus());
            try {
                bookingDto.setOrderId(booking.getOrderTable().getId());
            }
            catch(Exception e)
            {

            }
            return bookingDto;
        }






        public OrderTableDto convertToOrderDto(Order_Table orderTable) {

            OrderTableDto orderdto= new OrderTableDto();
            orderdto.setBookingId(orderTable.getBooking().getId());
            orderdto.setOrderId(Math.toIntExact(orderTable.getId()));
           // orderdto.setTransactionId(Math.toIntExact(orderTable.getTransactions().getId()));

            orderdto.setNumOfDays(orderTable.getNumOfDays());
            orderdto.setOwnerName(orderTable.getBooking().getProduct().getUser().getUserName());
            orderdto.setRenterName(orderTable.getBooking().getUser().getUserName());
            orderdto.setProductName(orderTable.getBooking().getProduct().getPname());
            orderdto.setPdfname(orderTable.getPdfname());
            orderdto.setPricePerDay(orderTable.getBooking().getProduct().getPriceperday());
            orderdto.setStartDate(orderTable.getBooking().getStartDate());
            orderdto.setEndDate(orderTable.getBooking().getEndDate());
            orderdto.setQuantity(orderTable.getBooking().getQuantity());
            orderdto.setSecurityDeposit(orderTable.getBooking().getProduct().getSecurityDeposite());
            orderdto.setServiceCharge(orderTable.getServiceCharge());
            orderdto.setTotal(orderTable.getTotal());
            return orderdto;
        }










}