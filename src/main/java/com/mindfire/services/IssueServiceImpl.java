package com.mindfire.services;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindfire.entities.IssueEntitty;
import com.mindfire.exception.CustomException;
import com.mindfire.repository.IssueRepositry;
import com.mindfire.request.IssueDto;
import com.mindfire.servicesinterface.IIssueService;

@Service
public class IssueServiceImpl implements IIssueService {

	@Autowired
	private IssueRepositry issueRepo;

	@Override
	public boolean createIssue(IssueDto dto) {
		IssueEntitty ent=new IssueEntitty();
		BeanUtils.copyProperties(dto, ent);
		IssueEntitty savedEntity = issueRepo.save(ent);
		return !Objects.isNull(savedEntity);
	}

	@Override
	public List<IssueDto> listAllIssue() {
		List<IssueDto> allIssues = issueRepo.findAll().stream().map((ent) -> {
			IssueDto dto = new IssueDto();
			BeanUtils.copyProperties(ent, dto);
			return dto;
		}).collect(Collectors.toList());
		return allIssues;
	}

	@Override
	public IssueDto fetchIssueById(int id) {
		IssueDto dto=new IssueDto();
		
		IssueEntitty issue = issueRepo.findById(id).orElseThrow(()->{
			throw new CustomException("No issue found with given id");
		});
		
		BeanUtils.copyProperties(issue, dto);
		
		return dto;
	}

	@Override
	public boolean editIssue(IssueDto dto) {
		IssueEntitty existingIssue = issueRepo.findById(dto.getId()).orElseThrow(()->{
			throw new CustomException("No issue found with given id");
		});
		
		BeanUtils.copyProperties(dto, existingIssue);
		
		IssueEntitty savedEntity = issueRepo.save(existingIssue);
		return !Objects.isNull(savedEntity);
		
	}

	@Override
	public boolean issueDeleteById(int id) {
		try {
			issueRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			
		}
	
		return false;
	}

}
