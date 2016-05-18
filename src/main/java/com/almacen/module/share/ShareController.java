package com.almacen.module.share;


import com.almacen.module.folder.service.FolderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

public class ShareController {

    @Inject
    private FolderService folderService;

    @RequestMapping(value = ShareUrls.SHARE, method = RequestMethod.GET)
    public void share() {

    }
}
