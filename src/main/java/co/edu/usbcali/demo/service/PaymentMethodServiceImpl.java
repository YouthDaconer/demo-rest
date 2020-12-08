package co.edu.usbcali.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.PaymentMethod;
import co.edu.usbcali.demo.repository.PaymentMethodRepository;

@Service
@Scope("singleton")
public class PaymentMethodServiceImpl implements PaymentMethodService {

	@Autowired
	PaymentMethodRepository paymentMethodRepository;

	@Override
	@Transactional(readOnly = true)
	public List<PaymentMethod> findAll() {
		return paymentMethodRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<PaymentMethod> findById(Integer id) throws Exception {
		return paymentMethodRepository.findById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<PaymentMethod> findAllEnable() throws Exception {
		return paymentMethodRepository.findAllEnable();
	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return paymentMethodRepository.count();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public PaymentMethod save(PaymentMethod entity) throws Exception {
		validate(entity);

		return paymentMethodRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public PaymentMethod update(PaymentMethod entity) throws Exception {
		validate(entity);

		return paymentMethodRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(PaymentMethod entity) throws Exception {
		if (entity == null) {
			throw new Exception("El entity es null");
		}

		if (entity.getPayId() == null) {
			throw new Exception("El payId es obligatorio");
		}

		paymentMethodRepository.findById(entity.getPayId()).ifPresent(paymentMethodRepository -> {
			if (paymentMethodRepository.getShoppingCarts() != null
					&& paymentMethodRepository.getShoppingCarts().isEmpty() == false) {
				throw new RuntimeException(
						"El paymentMethod con payId: " + entity.getPayId() + " tiene ShoppingCartMethods, no se puede borrar");
			}
		});

		paymentMethodRepository.deleteById(entity.getPayId());

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Integer id) throws Exception {
		if (id == null) {
			throw new Exception("El payId es obligatorio");
		}
		
		if(paymentMethodRepository.existsById(id)) {
			delete(paymentMethodRepository.findById(id).get());
		}
	}

	@Override
	public void validate(PaymentMethod entity) throws Exception {
		if (entity == null) {
			throw new Exception("El entity es null");
		}

		if (entity.getName() == null || entity.getName().isBlank() == true) {
			throw new Exception("El name es obligatorio");
		}

		if (entity.getEnable() == null || entity.getEnable().isBlank() == true) {
			throw new Exception("El enable es obligatorio");
		}
	}

}
