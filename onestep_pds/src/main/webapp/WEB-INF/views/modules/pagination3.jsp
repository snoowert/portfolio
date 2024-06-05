<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
  
<form id="jobForm">	
	<input type='hidden' name="page" value="" />
	<input type='hidden' name="perPageNum" value=""/>
	<input type='hidden' name="searchType" value="" />
	<input type='hidden' name="keyword" value="" />
</form>


<script>
function search_list(page){
	//alert(page);
	let perPageNum = 10;
	let searchType = document.querySelector('select[name="searchType"]').value;
	let keyword = document.querySelector('input[name="keyword"]').value;
	
	//alert(perPageNum+":"+searchType+":"+keyword);
	
	let form = document.querySelector("#jobForm");
	form.page.value=page;
	form.perPageNum.value=perPageNum;
	form.searchType.value = searchType;
	form.keyword.value = keyword;
	
	console.log(page,perPageNum,searchType,keyword);
	//console.log($(form).serialize());
	
	//form.action="";
	//form.method="";
	form.submit();
}	
</script>


