<!--A Design by W3layouts
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title><c:out value="${user.username}"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script src="js/script.js"></script>
<script type="text/javascript" src="js/jquery.openCarousel.js"></script>
<link href='http://fonts.googleapis.com/css?family=Coda' rel='stylesheet' type='text/css'>
 <script>
    $(function () {

      // Slideshow 1
      $("#slider1").responsiveSlides({
        maxwidth:1600,
        speed: 600
      });
});
 </script>
 
 <!------ menu ------>
  <script type="text/javascript" src="js/jquery.smint.js"></script>
  <script type="text/javascript">	
   $(document).ready( function() {
    $('.subMenu').smint({
    	'scrollSpeed' : 1000
    });
});

</script>
    <!------End menu ------>
    <!--- Hover Efeect --------------->
      <link href="css/image-hover.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery.adipoli.min.js" type="text/javascript"></script>
          <script type="text/javascript">
            
            $(function(){
                 $('.row2').adipoli({
                    'startEffect' : 'overlay',
                    'hoverEffect' : 'sliceDown'
                });
                $('.row6').adipoli({
                    'startEffect' : 'grayscale',
                    'hoverEffect' : 'normal'
                });
            });
            
        </script>
         <!--- End Hover Efeect --------------->
    <!------ Light Box ------>
<link rel="stylesheet" href="css/swipebox.css">
<script src="js/ios-orientationchange-fix.js"></script> 
<script src="js/jquery.swipebox.min.js"></script> 
<script type="text/javascript">
		jQuery(function($) {
			$(".swipebox").swipebox();
		});
	</script>
	<!------ Eng Light Box ------>
</head>
<body>
   <!-- Header -->
	<div class="header sTop">
		 <div class="header_top">
		 	  <div class="wrap">	 			
				<div class="logo">
					<a href="index.html"><img src="images/leafsoft.png"></a>
				</div>	
				<div class="header-top-right">
					<div class="social-icons">						
		                <ul>
		                    <li><a class="facebook" href="#" target="_blank"> </a></li>
		                    <li><a class="twitter" href="#" target="_blank"></a></li>
		                    <li><a class="googleplus" href="#" target="_blank"></a></li>
		                    <li><a class="pinterest" href="#" target="_blank"></a></li>
		                    <li><a class="dribbble" href="#" target="_blank"></a></li>
		                    <li><a class="vimeo" href="#" target="_blank"></a></li>
		                    <div class="clear"></div>
		                </ul>
		 		    </div>
		 		    <div class="login_button">
					<div class="call">
						<p>Help : + 00 - 000 - 00000</p>
					</div>
					    <ul>
					   		 <li><img src="images/login.png" alt=""><a href="#member">Login</a></li>
					   		 <li><img src="images/chat.png" alt=""><a href="">Live Chat</a></li>
					    </ul>
				  </div>
				</div>
 				<div class="clear"></div>		   
 	   		</div>
 	   </div> 	  	          
 	           <div class="subMenu">
 	           	  <div class="wrap">
					<div class="inner">
						<ul>
						<li> <a href="#" id="sTop" class="subNavBtn">Home</a> </li>
						<li> <a href="#" id="s1" class="subNavBtn">About</a> </li>
						<li> <a href="#" id="s2" class="subNavBtn">Staff</a> </li>
						<li> <a href="#" id="s3" class="subNavBtn">Events</a> </li>
						<li>  <a href="#" id="s4" class="subNavBtn">Services</a> </li>
						<li> <a href="#" id="s5" class="subNavBtn end">Contact</a> </li>
						<div class="clear"></div>
					 </ul>
					</div>
					</div>
				</div>	
			<div class="header_bottom">
				<div class="wrap">
				<div class="image-slider">
				    <ul class="rslides" id="slider1">
				       <li><img src="images/4.jpg" alt="" /></li>
				        <li><img src="images/1.jpg" alt="" /></li>
				      <li><img src="images/2.jpg" alt="" /></li>
				      <li><img src="images/3.jpg" alt="" /></li>				     
				    </ul>
			 </div>
			</div>
		 </div>	
	 </div>
	   <!-- Ends Header -->
 <!-- Main Content -->
 <div class="main">
 	<div class="content">
 		<div class="wrap">
 			<!----------- About page ------------>
          <div class="section s1">
				<div class="section group">
				<div class="grid_1_of_4 images_1_of_4">
					 <img src="images/grid_1_of_4-img1.jpg" alt="" />
					 <h3>Lorem Ipsum is simply </h3>
					 <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
				     <div class="button"><span><a href="#">Read More</a></span></div>
				</div>
				<div class="grid_1_of_4 images_1_of_4">
					 <img src="images/grid_1_of_4-img2.jpg" alt="" />
					 <h3>Lorem Ipsum is simply </h3>
					 <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
				     <div class="button"><span><a href="#">Read More</a></span></div>
				</div>
				<div class="grid_1_of_4 images_1_of_4">
					 <img src="images/grid_1_of_4-img3.jpg" alt="" />
					 <h3>Lorem Ipsum is simply </h3>
					 <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
				     <div class="button"><span><a href="#">Read More</a></span></div>
				</div>
				<div class="grid_1_of_4 images_1_of_4">
					 <img src="images/grid_1_of_4-img4.jpg" alt="" />
					 <h3>Lorem Ipsum is simply </h3>
					 <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
				     <div class="button"><span><a href="#">Read More</a></span></div>
				</div>
			</div>
	      <div class="grid1">
	    		<h2>Welcome</h2>
	    		<img src="images/welcome-img.jpg">
	   	 		<p><span>Lorem Ipsum is simply dummy text of the printing and typesetitng industry long established.</span></p>
	   		    <p>It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution.It is a long established fact that a reader.</p>
	     </div>			   			   
	          <div class="grid2">
			    	<h2>what we do</h2>
					    <ul>
			              	<li>Sed ut perspiciatis unde error content of a page</li>
			              	<li>voluptate velit quam molestiae content of a page</li>
			              	<li>who do not know how to pleasure content of a page</li>
			              	<li>who are beguiled and demoralized content of a page</li>
			              	<li>Sed ut perspiciatis unde error simply dummy text</li>
			              	<li>voluptate velit quam molestiae simply dummy text</li>
			              	<li>who do not know how to pleasure simply dummy text</li>
			              	<li>who are beguiled and demoralized simply dummy text</li>  
			              	<li>Sed ut perspiciatis unde error simply dummy text</li>
			              	<li>voluptate velit quam molestiae simply dummy text</li>          	
			             </ul>
					</div>
		   <div class="grid3">
			    	<h2>Latest News</h2>
					   <div class="tweet_data">
						<div class="tweet_img">
							<img src="images/latest-news-img1.jpg" alt="">
						</div>
							<div class="tweet_desc">
								 <h4>June 24th, 2013</h4>
							<p>Integer eget tortor et elit venenatis auctor morbi turpis nulla Lorem Ipsum is simply dummy text of the printing</p>
							</div>
						<div class="clear"></div>
					</div>
					<div class="tweet_data">
						<div class="tweet_img">
							<img src="images/latest-news-img3.jpg" alt="">
						</div>
							<div class="tweet_desc">
								 <h4>June 24th, 2013</h4>
							<p>Integer eget tortor et elit venenatis auctor morbi turpis nulla Lorem Ipsum is simply dummy text of the printing</p>
							</div>
						<div class="clear"></div>
					</div>
					<div class="tweet_data">
						<div class="tweet_img">
							<img src="images/latest-news-img2.jpg" alt="">
						</div>
							<div class="tweet_desc">
								 <h4>June 24th, 2013</h4>
							<p>Integer eget tortor et elit venenatis auctor morbi turpis nulla Lorem Ipsum is simply dummy text of the printing</p>
							</div>
						<div class="clear"></div>
					</div>
					<p><a href="#">View all</a></p>
		     </div>
        <div class="clear"></div>
  </div>
<!----------- End About page ------------> 
<!----------- Start Staff page ------------>             
	<div class="section s2">
	      	<h2>Our Staff</h2>
	        <div class="section group">			
					<div class="leftsidebar span_3_of_1">
						<h3>Lorem Ipsum is simply dummy text dolore magna aliqua</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
	 				</div>				
				<div class="cont span_1_of_2">
					<div class="grid images_3_of_2">
						<img src="images/staff-img1.jpg" alt="" />
					</div>
					<div class="desc span_3_of_2">
						<h3>Lorem Ipsum is simply dummy text </h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
						<p><a href="#">More Info</a></p>
					</div>
				</div>	
			</div>
			<div class="staff-desc">
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
						<div class="staff-images">
				<div class="ocarousel example_photos" data-ocarousel-perscroll="3">
                <div class="ocarousel_window">
                   <a href="images/staff-img_b.jpg" class="swipebox" title="Nuke"> <img src="images/staff-img.jpg" class="img-style row2" alt="" /></a>
                   <a href="images/staff-img2_b.jpg" class="swipebox" title="Nuke"><img src="images/staff-img2.jpg" class="img-style row2" alt="" /></a>
                   <a href="images/staff-img3_b.jpg" class="swipebox" title="Nuke"><img src="images/staff-img3.jpg" class="img-style row2" alt="" /></a>
                   <a href="images/staff-img4_b.jpg" class="swipebox" title="Nuke"><img src="images/staff-img4.jpg" class="img-style row2" alt="" /></a>
                   <a href="images/staff-img5_b.jpg" class="swipebox" title="Nuke"><img src="images/staff-img5.jpg" class="img-style row2" alt="" /></a>
                   <a href="images/staff-img6_b.jpg" class="swipebox" title="Nuke"><img src="images/staff-img6.jpg" class="img-style row2" alt="" /></a>
                   <a href="images/staff-img_b.jpg" class="swipebox" title="Nuke"><img src="images/staff-img7.jpg" class="img-style row2" alt="" /></a>
                   <a href="images/staff-img2_b.jpg" class="swipebox" title="Nuke"> <img src="images/staff-img8.jpg" class="img-style row2" alt="" /></a>
                   <a href="images/staff-img4_b.jpg" class="swipebox" title="Nuke"><img src="images/staff-img9.jpg" class="img-style row2" alt="" /></a>
                   <a href="images/staff-img10_b.jpg" class="swipebox" title="Nuke"> <img src="images/staff-img10.jpg" class="img-style row2" alt="" /></a>
                   <a href="images/staff-img11_b.jpg" class="swipebox" title="Nuke"> <img src="images/staff-img11.jpg" class="img-style row2" alt="" /></a>
                   <a href="images/staff-img_b.jpg" class="swipebox" title="Nuke"> <img src="images/staff-img12.jpg" class="img-style row2" alt="" /></a>
                </div>
               <span>           
                <a href="#" data-ocarousel-link="left" style="float: left;" class="prev"> </a>
                <a href="#" data-ocarousel-link="right" style="float: right;" class="next"> </a>
               </span>
			</div>
	     </div>	
			   		<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>		
			</div>
	   </div>
			<!----------- End Staff page ------------>
			
			<!--------- Start Events Page --------->
			<div class="section s3">
					<h2>Recent Events</h2>
					<div class="section group">
				<div class="grid_1_of_4 images_1_of_4">
					 <a href="images/event-img1_b.jpg" class="swipebox" title="Nuke"> <img src="images/event-img1.jpg" class="img-style row6" alt="" /></a>
					 <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
				</div>
				<div class="grid_1_of_4 images_1_of_4">
					<a href="images/event-img2_b.jpg" class="swipebox" title="Nuke"> <img src="images/event-img2.jpg" class="img-style row6" alt="" /></a>
					 <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
				</div>
				<div class="grid_1_of_4 images_1_of_4">
					 <a href="images/event-img3_b.jpg" class="swipebox" title="Nuke"> <img src="images/event-img3.jpg" class="img-style row6" alt="" /></a>
					 <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
				</div>
				<div class="grid_1_of_4 images_1_of_4">
					 <a href="images/event-img4_b.jpg" class="swipebox" title="Nuke"> <img src="images/event-img4.jpg" class="img-style row6" alt="" /></a>
					 <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
				</div>
			</div>
			<div class="section group">
				<div class="grid_1_of_4 images_1_of_4">
					<a href="images/event-img5_b.jpg" class="swipebox" title="Nuke"> <img src="images/event-img5.jpg" class="img-style row6" alt="" /></a>
					 <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
				</div>
				<div class="grid_1_of_4 images_1_of_4">
					<a href="images/event-img6_b.jpg" class="swipebox" title="Nuke"> <img src="images/event-img6.jpg" class="img-style row6" alt="" /></a>
					 <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
				</div>
				<div class="grid_1_of_4 images_1_of_4">
					<a href="images/event-img7_b.jpg" class="swipebox" title="Nuke"> <img src="images/event-img7.jpg" class="img-style row6" alt="" /></a>
					 <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
				</div>
				<div class="grid_1_of_4 images_1_of_4">
					 <a href="images/event-img8_b.jpg" class="swipebox" title="Nuke"> <img src="images/event-img8.jpg" class="img-style row6" alt="" /></a>
					 <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
				</div>
			</div>
			</div>
			<!--------- End Start Events Page --------->
			<!------- Start Services page ------------->
			<div class="section s4">
				<h2>What We Offer</h2>
			<div class="section group">
				<div class="col_1_of_4 span_1_of_4">
					<div class="img-heading">
				       <div class="service-img">
							<img src="images/service-icon1.png" alt="" />
					    </div>
					       		<div class="heading">								
									<h4>Service 01</h4>
							     </div>
							 <div class="clear"></div>
						</div>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
				       <ul>
			              	<li>Sed ut perspiciatis unde error</li>
			              	<li>voluptate velit quam molestiae</li>
			              	<li>who do not know how to pleasure</li>
			              	<li>who are beguiled and demoralized</li>
			              	<li>Sed ut perspiciatis unde error</li>
			              	<li>voluptate velit quam molestiae</li>
			              	<li>who do not know how to pleasure</li>
			              	<li>who are beguiled and demoralized</li>  
			              	<li>Sed ut perspiciatis unde error</li>
			              	<li>voluptate velit quam molestiae</li>          	
			           </ul>
				</div>
				<div class="col_1_of_4 span_1_of_4">
					<div class="img-heading">
				       <div class="service-img">
							<img src="images/service-icon2.png" alt="" />
					    </div>
					       		<div class="heading">								
									<h4>Service 02</h4>
							     </div>
							 <div class="clear"></div>
						</div>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
					<ul>
			              	<li>Sed ut perspiciatis unde error</li>
			              	<li>voluptate velit quam molestiae</li>
			              	<li>who do not know how to pleasure</li>
			              	<li>who are beguiled and demoralized</li>
			              	<li>Sed ut perspiciatis unde error</li>
			              	<li>voluptate velit quam molestiae</li>
			              	<li>who do not know how to pleasure</li>
			              	<li>who are beguiled and demoralized</li>  
			              	<li>Sed ut perspiciatis unde error</li>
			              	<li>voluptate velit quam molestiae</li>          	
			           </ul>
				</div>
				<div class="col_1_of_4 span_1_of_4">
					<div class="img-heading">
				       <div class="service-img">
							<img src="images/service-icon3.png" alt="" />
					    </div>
					       		<div class="heading">								
									<h4>Service 03</h4>
							     </div>
							 <div class="clear"></div>
						</div>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
					<ul>
			              	<li>Sed ut perspiciatis unde error</li>
			              	<li>voluptate velit quam molestiae</li>
			              	<li>who do not know how to pleasure</li>
			              	<li>who are beguiled and demoralized</li>
			              	<li>Sed ut perspiciatis unde error</li>
			              	<li>voluptate velit quam molestiae</li>
			              	<li>who do not know how to pleasure</li>
			              	<li>who are beguiled and demoralized</li>  
			              	<li>Sed ut perspiciatis unde error</li>
			              	<li>voluptate velit quam molestiae</li>          	
			           </ul>
				</div>
				<div class="col_1_of_4 span_1_of_4">
					<div class="img-heading">
				       <div class="service-img">
							<img src="images/service-icon4.png" alt="" />
					    </div>
					       		<div class="heading">								
									<h4>Service 04</h4>
							     </div>
							 <div class="clear"></div>
						</div>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
					<ul>
			              	<li>Sed ut perspiciatis unde error</li>
			              	<li>voluptate velit quam molestiae</li>
			              	<li>who do not know how to pleasure</li>
			              	<li>who are beguiled and demoralized</li>
			              	<li>Sed ut perspiciatis unde error</li>
			              	<li>voluptate velit quam molestiae</li>
			              	<li>who do not know how to pleasure</li>
			              	<li>who are beguiled and demoralized</li>  
			              	<li>Sed ut perspiciatis unde error</li>
			              	<li>voluptate velit quam molestiae</li>          	
			           </ul>
				</div>
			</div>
			</div>
			<!------ End Services Page ------------>
			<!------ Start Contact Page ---------->
			<div class="section s5">
				<h2>Get In Touch</h2>
			<div class="section group">
				<div class="col span_2_of_3">
				  <div class="contact-form">
				  	<h3>Contact Us</h3>
				  			<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
				  	<div class="left-form">
					    <form>
					    	<div>
						    	<span><label>Name</label></span>
						    	<span><input type="text" value=""></span>
						    </div>
						    <div>
						    	<span><label>E-mail</label></span>
						    	<span><input type="text" value=""></span>
						    </div>
						    <div>
						     	<span><label>Fax</label></span>
						    	<span><input type="text" value=""></span>
						    </div>
						    </form>
						</div>
						<div class="right-form">
					   <form>
							<div>
						    	<span><label>Subject</label></span>
						    	<span><textarea> </textarea></span>
						    </div>
						   <div>
						   		<span><input type="submit" value="Submit"></span>
						  </div>
					    </form>
				  </div>
				  <div class="clear"></div>
  				</div>
  			</div>
				<div class="col span_1_of_3">
					<div class="contact_info">
    	 				<h3>Find Us Here</h3>
					    	  <div class="map">
							   	    <iframe width="100%" height="170" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://maps.google.co.in/maps?f=q&amp;source=s_q&amp;hl=en&amp;geocode=&amp;q=Lighthouse+Point,+FL,+United+States&amp;aq=4&amp;oq=light&amp;sll=26.275636,-80.087265&amp;sspn=0.04941,0.104628&amp;ie=UTF8&amp;hq=&amp;hnear=Lighthouse+Point,+Broward,+Florida,+United+States&amp;t=m&amp;z=14&amp;ll=26.275636,-80.087265&amp;output=embed"></iframe><br><small><a href="https://maps.google.co.in/maps?f=q&amp;source=embed&amp;hl=en&amp;geocode=&amp;q=Lighthouse+Point,+FL,+United+States&amp;aq=4&amp;oq=light&amp;sll=26.275636,-80.087265&amp;sspn=0.04941,0.104628&amp;ie=UTF8&amp;hq=&amp;hnear=Lighthouse+Point,+Broward,+Florida,+United+States&amp;t=m&amp;z=14&amp;ll=26.275636,-80.087265" style="color:#666;text-align:left;font-size:12px">View Larger Map</a></small>
							  </div>
      				</div>
      			<div class="company_address">
				     	<h3>Company Information :</h3>
						    	<p><c:out value="${org.orgname}"/></p>
						   		<p><c:out value="${org.address}"/></p>
						   		<p><c:out value="${org.state}"/></p>
				   		<p>Phone:(00) 222 666 444</p>
				   		<p>Fax: (000) 000 00 00 0</p>
				 	 	<p>Email: <span>info@mycompany.com</span></p>
				   		<p>Follow on: <span>Facebook</span>, <span>Twitter</span></p>
				   </div>
				 </div>
			  </div>
			</div>
			<!-------- End Contact page ------------->
 </div>
 </div>
</div>
 <!-- Footer Start -->
 <div class="footer">
	<div class="wrap">
		<div class="footer-grides">
		   <div class="foot-1" style="margin-left:0;text-align:center">
			<img src="images/footer-logo.png">
			<h4>© All rights Reseverd</h4>
        </div>
		<div class="foot-1">
			<h3>Latest News</h3>
				<div class="news">
					<marquee behavior="scroll" direction="up" onmouseover="this.stop();" onmouseout="this.start();">				
						<p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.<br/>
							 <a href="#">4 hours ago</a>&nbsp;<span>by</span>&nbsp;<a href="">Smith</a></p>
							<p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget,<br />
								<a href="#">8 hours ago</a>&nbsp;<span>by</span>&nbsp;<a href="">Smith</a></p>
						<p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget,<br />
								<a href="#">8 hours ago</a>&nbsp;<span>by</span>&nbsp;<a href="">Smith</a></p>
					</marquee>
				</div>
		</div>
         <div class="foot-1">
		    	<h3>Call Us Now</h3>
		    	      <p class="call">+11 - 222 - 345678</p>
		    	      <p class="call">+99 - 999 - 999999</p>
		    </div>
		<div class="clear"></div>
	</div>
  </div>
		  <div class="copy_right">
				<p>Company Name © All rights Reseverd | Design by  <a href="http://www.w3layouts.com">W3Layouts</a></p>
		   </div>
	</div>
</body>
</html>

