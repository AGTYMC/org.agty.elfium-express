package org.agty.elfiumexpress.modules.express.service;

import org.agty.elfiumexpress.api.entity.SortBody;
import org.agty.elfiumexpress.modules.express.entity.ExpressGroup;
import org.agty.elfiumexpress.modules.express.repository.ExpressGroupRepo;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ExpressGroupService {
    private final ExpressGroupRepo expressGroupRepo;

    public ExpressGroupService(ExpressGroupRepo expressGroupRepo) {
        this.expressGroupRepo = expressGroupRepo;
    }

    public void save(ExpressGroup expressGroup) {
        expressGroupRepo.save(expressGroup);
    }

    public ExpressGroup getGroup(Long id) {
        return expressGroupRepo.getById(id);
    }

    public List<ExpressGroup> getGroups(Long idRootGroup) {
        return expressGroupRepo.findAll(idRootGroup);
    }

    public void del(Long idGroup) {
        expressGroupRepo.del(idGroup);
    }

    public LinkedList<ExpressGroup> fullPath(Long idGroup) {
        return expressGroupRepo.fullPath(idGroup);
    }

    public void sort(SortBody[] bodies) {
        expressGroupRepo.sort(bodies);
    }
}
