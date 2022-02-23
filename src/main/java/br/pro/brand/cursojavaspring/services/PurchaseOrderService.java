package br.pro.brand.cursojavaspring.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pro.brand.cursojavaspring.domain.BankSlipPayment;
import br.pro.brand.cursojavaspring.domain.OrderDetail;
import br.pro.brand.cursojavaspring.domain.Product;
import br.pro.brand.cursojavaspring.domain.PurchaseOrder;
import br.pro.brand.cursojavaspring.domain.enums.PaymentStatus;
import br.pro.brand.cursojavaspring.exceptions.ObjectNotFoundException;
import br.pro.brand.cursojavaspring.repositories.OrderDetailRepository;
import br.pro.brand.cursojavaspring.repositories.PaymentRepository;
import br.pro.brand.cursojavaspring.repositories.PurchaseOrderRepository;

@Service
public class PurchaseOrderService {

    private Integer idProd;
    
    @Autowired
    private PurchaseOrderRepository rep;

    @Autowired
	private BankSlipService bankSlipService;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Autowired
	private ProductService productService;

    public PurchaseOrder search(Integer id) {
        Optional<PurchaseOrder> obj = rep.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
            "Object not found! Id: " + id + 
            " ,Type: " + PurchaseOrder.class.getName(), null)
        );
    }

    public PurchaseOrder insert(PurchaseOrder obj) {
		obj.setId(null);
		obj.setCreatedAt(new Date());
		obj.getPayment().setPaymentStatus(PaymentStatus.PENDENTE);
		obj.getPayment().setPurchaseOrder(obj);
		if (obj.getPayment() instanceof BankSlipPayment) {
			BankSlipPayment pagto = (BankSlipPayment) obj.getPayment();
			bankSlipService.fillBankSlipPayment(pagto, obj.getCreatedAt());
		}
		obj = rep.save(obj);
		paymentRepository.save(obj.getPayment());
		for (OrderDetail ip : obj.getOrderDetails()) {
			ip.setDiscount(0.0);
            idProd = ip.getProduct().getId();
            Product objProd = productService.find(idProd);
			ip.setPrice(objProd.getPrice());
			ip.setPurchaseOrder(obj);
		}
		orderDetailRepository.saveAll(obj.getOrderDetails());
		return obj;
	}
    
}
