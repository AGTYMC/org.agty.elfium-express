package org.agty.elfiumexpress.modules.express.service;

import org.agty.elfiumexpress.api.entity.ActionItem;
import org.agty.elfiumexpress.modules.express.repository.ExpressGroupRepo;
import org.agty.elfiumexpress.modules.express.repository.ExpressPanelRepo;
import org.springframework.stereotype.Service;

@Service
public class ExpressMoveService {
    ExpressPanelRepo expressPanelRepo;
    ExpressGroupRepo expressGroupRepo;

    public ExpressMoveService(ExpressPanelRepo expressPanelRepo, ExpressGroupRepo expressGroupRepo) {
        this.expressPanelRepo = expressPanelRepo;
        this.expressGroupRepo = expressGroupRepo;
    }

    public void move(ActionItem[] actionItems) {
        for(ActionItem actionItem : actionItems) {
            if (actionItem == null) continue;

            if (actionItem.getObject().equals("panel")) {
                expressPanelRepo.move(actionItem);
            }

            if (actionItem.getObject().equals("group")) {
                expressGroupRepo.move(actionItem);
            }
        }
    }
}
