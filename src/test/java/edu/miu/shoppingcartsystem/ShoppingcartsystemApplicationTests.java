package edu.miu.shoppingcartsystem;

import edu.miu.shoppingcartsystem.model.Vendor;
import edu.miu.shoppingcartsystem.repository.VendorRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ShoppingcartsystemApplicationTests {

    @Autowired
    private VendorRepository vrepo;
    @Test
    @Order(1)
    public void testVendorPostAPI(){
        Vendor vendor=new Vendor();
        vendor.setVendorID(1l);
        vendor.setVendorname("Sushanta");
        vendor.setAddress("Fairfield");
        vendor.setPhone("12345");
        vrepo.save(vendor);
        assertNotNull(vrepo.findById(1l).get());
    }

    @Test
    @Order(2)
    public void testVendorGetAllAPI(){
        List<Vendor> vendorList=vrepo.findAll();
        assertThat(vendorList).size().isGreaterThan(0);
    }

    @Test
    @Order(3)
    public void testVendorGetByIdAPI(){
        Vendor vendor=vrepo.findById(1l).get();
        assertEquals("12345",vendor.getPhone());
    }

    @Test
    @Order(4)
    public void testVendorPutAPI(){
        Vendor vendor=vrepo.findById(1l).get();
        vendor.setVendorname("Debodatta");
        vrepo.save(vendor);
        assertNotEquals("Sushanta",vrepo.findById(1l).get().getVendorname());
    }

    @Test
    @Order(5)
    public void testVendorDeleteAPI(){
        vrepo.deleteById(7l);
        assertThat(vrepo.existsById(7l)).isFalse();
    }
}
