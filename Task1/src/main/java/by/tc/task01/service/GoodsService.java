package by.tc.task01.service;

import by.tc.task01.entity.Sellable;
import by.tc.task01.entity.criteria.Criteria;
import by.tc.task01.exception.TaskException;

import java.util.List;

public interface GoodsService {
    <E> List<Sellable> find(Criteria<E> criteria) throws TaskException;
}
