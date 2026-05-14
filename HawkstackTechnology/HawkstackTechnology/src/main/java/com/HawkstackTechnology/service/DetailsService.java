package com.HawkstackTechnology.service;



import java.util.List;

import com.HawkstackTechnology.entity.Details;

public interface DetailsService {

    Details create(Details details);

    List<Details> getAll();

    Details update(Long id, Details details);

    void delete(Long id);
}