package com.hotelBooking.issueBill.test;

import com.hotelBooking.issueBill.models.IssueBill;
import com.hotelBooking.issueBill.models.Payment;
import com.hotelBooking.issueBill.repo.BillRepo;
import com.hotelBooking.issueBill.service.BillServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class BillServicesTest {

    @Mock
    private BillRepo repo;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private BillServices billServices;

    private Payment payment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        // Initialize your Payment object with test data
        payment = new Payment();
        payment.setTransactionId(1);
        payment.setTotal_payment(100.00);
        payment.setTaxes(0.1);
        payment.setNumber_rooms(2);
    }

    @Test
    void testGenerateBill() {
        // Mock the REST call to return the payment object
        when(restTemplate.getForObject(any(String.class), eq(Payment.class))).thenReturn(payment);
        
        // Mock the save method of the repository
        when(repo.save(any(IssueBill.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Call the method under test
        IssueBill bill = billServices.generateBill(1);

        // Assertions to check if the bill is created correctly
        assertNotNull(bill);
        assertEquals("BILL-" + payment.getTransactionId(), bill.getBillingNumber());
        assertEquals(payment, bill.getPayment());
        assertEquals(LocalDate.now(), bill.getDate());
        verify(restTemplate, times(1)).getForObject(any(String.class), eq(Payment.class));
        verify(repo, times(1)).save(any(IssueBill.class));
    }

    @Test
    void testGenerateBillPaymentNotFound() {
        // Mock the REST call to return null to simulate payment not found
        when(restTemplate.getForObject(any(String.class), eq(Payment.class))).thenReturn(null);

        // Expecting a RuntimeException when the payment is not found
        RuntimeException exception = assertThrows(RuntimeException.class, () -> billServices.generateBill(1));
        assertEquals("Payment with ID: 1 not found.", exception.getMessage());
    }
}
