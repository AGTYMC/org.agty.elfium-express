package org.agty.elfiumexpress.modules.express.service;

import org.agty.elfiumexpress.modules.express.common.ExpressType;
import org.agty.elfiumexpress.modules.express.repository.ExpressTypeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpressTypeService {
    public final ExpressTypeRepo expressTypeRepo;

    public ExpressTypeService(ExpressTypeRepo expressTypeRepo) {
        this.expressTypeRepo = expressTypeRepo;
    }

    public List<ExpressType> findAll() {
        return expressTypeRepo.findAll();
    }
}
