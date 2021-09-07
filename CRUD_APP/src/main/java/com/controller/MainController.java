package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import com.dao.ProductDao;
import com.model.Product;

@Controller
public class MainController {

	@Autowired
	private ProductDao dao;

	@RequestMapping("/home")
	public String home(Model model) {

		List<Product> products = dao.getProducts();
		model.addAttribute("product", products);

		return "home";
	}

//	show add product form

	@RequestMapping("/add-product")
	public String addProduct(Model model) {
		model.addAttribute("title", "Add_Products..!");
		return "add-product-form";
	}

//	handle add product form

	@RequestMapping(value = "/handle-product", method = RequestMethod.POST)
	public RedirectView handleproduct(@ModelAttribute Product product, HttpServletRequest request) {
		System.out.println(product);
		dao.createProduct(product);
		RedirectView view = new RedirectView();
		view.setUrl(request.getContextPath() + "/home");
		return view;
	}

//	delete handler
	@RequestMapping("/delete/{productId}")
	public RedirectView deleteHandler(@PathVariable("productId") int prid, HttpServletRequest request) {
		this.dao.deleteProduct(prid);
		RedirectView view = new RedirectView();
		view.setUrl(request.getContextPath() + "/home");
		return view;

	}

//	update handler
	@RequestMapping("/update/{productId}")
	public String updateForm(@PathVariable("productId") int pid, Model m) {
		Product product = this.dao.getProduct(pid);
		m.addAttribute("product", product);
		return "update-form";
	}

}
