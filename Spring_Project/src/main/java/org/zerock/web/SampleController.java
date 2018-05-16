package org.zerock.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.zerock.VO.ProductVO;;

@Controller
public class SampleController {

	private static final Logger logger = 
			LoggerFactory.getLogger(SampleController.class);
	
	@RequestMapping("/doA")
	public void doA() {
		logger.info("doA called....");
	}
	
	@RequestMapping("/doB")
	public void doB() {
		logger.info("doB called....");
	}
	
	@RequestMapping("/doD")
	public String doC(Model model) {
		logger.info("doD called....");
		
		ProductVO product = new ProductVO("Sample Product", "10000");
		product.setMoney("30000");
		product.setName("test");
		logger.info(product.getName());
		logger.info(product.getMoney());
		model.addAttribute("product", product);
		return "result";
	}
}
