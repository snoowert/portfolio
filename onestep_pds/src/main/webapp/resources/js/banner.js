var myCarousel = document.getElementById('myCarousel');
  var carousel = new bootstrap.Carousel(myCarousel, {
    interval: 4000, // 4초(4000ms)마다 다음 슬라이드로 넘어감
    wrap: true // 마지막 슬라이드에서 첫 번째 슬라이드로 돌아감
  });