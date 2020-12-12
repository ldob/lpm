package eu.ldob.lpm.be.service;

import eu.ldob.lpm.be.model.type.EPriority;
import eu.ldob.lpm.be.model.type.EProjectStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ValuesService {

    public List<EPriority> findAllPriorities() {
        return Arrays.asList(EPriority.values());
    }

    public List<EProjectStatus> findAllStatus() {
        return Arrays.asList(EProjectStatus.values());
    }
}
