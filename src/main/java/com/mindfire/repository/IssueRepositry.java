package com.mindfire.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindfire.entities.Issue;

@Repository
public interface IssueRepositry extends JpaRepository<Issue, Serializable> {

}
