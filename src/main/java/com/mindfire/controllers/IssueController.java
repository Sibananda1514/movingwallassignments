package com.mindfire.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.constants.ResponseConstant;
import com.mindfire.request.IssueDto;
import com.mindfire.response.ApiResponce;
import com.mindfire.response.ErrorResonse;
import com.mindfire.servicesinterface.IIssueService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("issues/")
@Api(value = "Controller responsible for issue related functions")
public class IssueController {

	@Autowired
	private IIssueService service;

	@PostMapping
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Bearer dsfjbsdfjasjdfweuf_dsjfbdsfsd_342kbr2k", required = false, dataType = "string", paramType = "header"), })
	@ApiOperation(value = "Create a new Issue")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResonse.class) })

	public ResponseEntity<ApiResponce<Boolean>> createNewIssue(
			@ApiParam(value = "Request Body for Create New Issue", required = true) @RequestBody IssueDto requestObj) {
		boolean isIssueCreated = service.createIssue(requestObj);
		ApiResponce<Boolean> apiResponse = new ApiResponce<>();
		apiResponse.setResult(isIssueCreated);
		apiResponse.setMessage(ResponseConstant.ISSUE_CREATE);
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Bearer dsfjbsdfjasjdfweuf_dsjfbdsfsd_342kbr2k", required = false, dataType = "string", paramType = "header"), })
	@ApiOperation(value = "List all Issue")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResonse.class) })

	public ResponseEntity<ApiResponce<List<IssueDto>>> listAllIssuue() {
		List<IssueDto> listAllIssue = service.listAllIssue();
		ApiResponce<List<IssueDto>> apiResponse = new ApiResponce<>();
		apiResponse.setResult(listAllIssue);
		apiResponse.setMessage(ResponseConstant.ISSUE_LIST);
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping("/{id}")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Bearer dsfjbsdfjasjdfweuf_dsjfbdsfsd_342kbr2k", required = false, dataType = "string", paramType = "header"), })
	@ApiOperation(value = "List issue by Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResonse.class) })

	public ResponseEntity<ApiResponce<IssueDto>> listIssuueById(
			@ApiParam(value = "ID of the issue to list", required = true) @PathVariable(value = "id", required = true) Integer issueId) {
		IssueDto issue = service.fetchIssueById(issueId);
		ApiResponce<IssueDto> apiResponse = new ApiResponce<>();
		apiResponse.setResult(issue);
		apiResponse.setMessage(ResponseConstant.ISSUE_LIST);
		return ResponseEntity.ok(apiResponse);
	}

	@PutMapping("/{id}")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Bearer dsfjbsdfjasjdfweuf_dsjfbdsfsd_342kbr2k", required = false, dataType = "string", paramType = "header"), })
	@ApiOperation(value = "Edit issue by Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResonse.class) })

	public ResponseEntity<ApiResponce<Boolean>> editIssuueById(
			@ApiParam(value = "issue request body to edit", required = true) @RequestBody IssueDto requestObj) {
		boolean editIssue = service.editIssue(requestObj);
		ApiResponce<Boolean> apiResponse = new ApiResponce<>();
		apiResponse.setResult(editIssue);
		apiResponse.setMessage(ResponseConstant.ISSUE_EDIT);
		return ResponseEntity.ok(apiResponse);
	}

	@DeleteMapping("/{id}")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Bearer dsfjbsdfjasjdfweuf_dsjfbdsfsd_342kbr2k", required = false, dataType = "string", paramType = "header"), })
	@ApiOperation(value = "delete issue by Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResonse.class) })

	public ResponseEntity<ApiResponce<Boolean>> deleteIssuueById(
			@ApiParam(value = "Id of issue to delete", required = true) @PathVariable(value = "id") Integer issueId) {
		boolean issue = service.issueDeleteById(issueId);
		ApiResponce<Boolean> apiResponse = new ApiResponce<>();
		apiResponse.setResult(issue);
		apiResponse.setMessage(ResponseConstant.ISSUE_DELETE);
		return ResponseEntity.ok(apiResponse);
	}
}
