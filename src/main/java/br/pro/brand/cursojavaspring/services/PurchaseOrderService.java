package br.pro.brand.cursojavaspring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pro.brand.cursojavaspring.domain.PurchaseOrder;
import br.pro.brand.cursojavaspring.exceptions.ObjectNotFoundException;
import br.pro.brand.cursojavaspring.repositories.PurchaseOrderRepository;

@Service
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository rep;

    public PurchaseOrder search(Integer id) {
        Optional<PurchaseOrder> obj = rep.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
            "Object not found! Id: " + id + 
            " ,Type: " + PurchaseOrder.class.getName(), null)
        );
    }
    
}
