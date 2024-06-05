<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="myCarousel" class="carousel slide" data-ride="carousel">
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="<%=request.getContextPath() %>/resources/img/banner/개발자노트.png" class="d-block w-100" style="height: calc(100vh / 3); object-fit: cover;" alt="First slide">
    </div>
    <div class="carousel-item">
      <img src="<%=request.getContextPath() %>/resources/img/banner/타메인.png" class="d-block w-100" style="height: calc(100vh / 3); object-fit: cover;" alt="Second slide">
    </div>
  </div>
  <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>