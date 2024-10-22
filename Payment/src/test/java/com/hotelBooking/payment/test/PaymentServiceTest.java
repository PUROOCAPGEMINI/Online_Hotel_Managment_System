package com.hotelBooking.payment.test;


import com.hotelBooking.payment.model.Payment;
import com.hotelBooking.payment.repository.PaymentRepo;
import com.hotelBooking.payment.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PaymentServiceTest {

    @Mock
    private PaymentRepo payRepo;

    @InjectMocks
    private PaymentService paymentService;

    private Payment payment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        payment = new Payment(); // Initialize your Payment object
        payment.setTransactionId(1); // Set an ID for testing
        payment.setAmount(100.00); // Set an amount for testing
        // Add other necessary fields
    }

    @Test
    void testAddPayment() {
        // Mock the save method
        when(payRepo.save(any(Payment.class))).thenReturn(payment);

        String result = paymentService.addPayment(payment);

        assertEquals("Payment added successfully with transaction ID: " + payment.getTransactionId(), result);
        verify(payRepo, times(1)).save(payment);
    }

    @Test
    void testGetPaymentById() {
        when(payRepo.findById(1)).thenReturn(Optional.of(payment));

        Payment foundPayment = paymentService.getPaymentById(1);

        assertNotNull(foundPayment);
        assertEquals(payment.getTransactionId(), foundPayment.getTransactionId());
        verify(payRepo, times(1)).findById(1);
    }

    @Test
    void testGetPaymentByIdNotFound() {
        when(payRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(NullPointerException.class, () -> paymentService.getPaymentById(1));
        verify(payRepo, times(1)).findById(1);
    }
}
