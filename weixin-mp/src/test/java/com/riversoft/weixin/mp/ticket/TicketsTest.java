package com.riversoft.weixin.mp.ticket;

import com.riversoft.weixin.mp.ticket.bean.Ticket;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;

/**
 * Created by exizhai on 12/1/2015.
 */
public class TicketsTest {

    @Test
    public void testCreateTemporary(){
        Ticket ticket = Tickets.defaultTickets().temporary(600000, 100);
        Assert.assertNotNull(ticket);

        byte[] bytes = Tickets.defaultTickets().getQrcode(ticket.getTicket());
        File file = new File(FileUtils.getTempDirectory(), "qrcode.jpg");
        try {
            IOUtils.write(bytes, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreatePermInt(){
        Ticket ticket = Tickets.defaultTickets().permanent(123);
        Assert.assertNotNull(ticket);

        byte[] bytes = Tickets.defaultTickets().getQrcode(ticket.getTicket());
        File file = new File(FileUtils.getTempDirectory(), "qrcode.jpg");
        try {
            IOUtils.write(bytes, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreatePermIntTooLong(){
        Tickets.defaultTickets().permanent(99999999);
    }

    @Test
    public void testCreatePermString(){
        Ticket ticket = Tickets.defaultTickets().permanent("abc");
        Assert.assertNotNull(ticket);

        byte[] bytes = Tickets.defaultTickets().getQrcode(ticket.getTicket());
        File file = new File(FileUtils.getTempDirectory(), "qrcode.jpg");
        try {
            IOUtils.write(bytes, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreatePermStringTooLong(){
        Tickets.defaultTickets().permanent("1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
    }
}
