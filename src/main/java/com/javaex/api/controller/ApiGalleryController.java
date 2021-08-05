package com.javaex.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
@RequestMapping(value="/api/gallery")
public class ApiGalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	@ResponseBody
	@RequestMapping(value="/viewModal", method = {RequestMethod.GET, RequestMethod.POST})
	public GalleryVo viewModal(@RequestParam("no") int no) {
		System.out.println("[ApiGalleryController.viewModal()]");
		
		GalleryVo galleryVo = galleryService.viewModal(no);
		return galleryVo;
	}

}
