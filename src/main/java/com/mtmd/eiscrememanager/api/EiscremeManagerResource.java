package com.mtmd.eiscrememanager.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "eiscrememanager")
public abstract class EiscremeManagerResource {
}
