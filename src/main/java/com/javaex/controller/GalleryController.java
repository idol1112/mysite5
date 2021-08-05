package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
@RequestMapping(value = "/gallery")
public class GalleryController {
	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("[GalleryController.list]");
		
		List<GalleryVo> galleryVo = galleryService.getList();
		System.out.println(galleryVo);
		
		model.addAttribute("galVo" ,galleryVo);
		
		return "gallery/list";
	}
	
	@RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
	public String add(@RequestParam("file") MultipartFile file, 
					  @ModelAttribute GalleryVo galleryVo) {
		System.out.println("[GalleryController.add]");

		//db저장
		String path = galleryService.add(galleryVo, file);
		
		//하드디스크에 저장
		galleryService.restore(file, path);
		
		return"redirect:/gallery/list";
	}
}
