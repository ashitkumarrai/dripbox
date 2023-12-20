package com.example.dripbox.repository;


import com.example.dripbox.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;







@Repository
public interface FileRepository extends JpaRepository<File, String> {
    
}
