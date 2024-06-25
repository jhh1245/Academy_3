<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/css">
.container{ 
	width: 100%;
padding-right : var(--bs-gutter-x,.75rem);
padding-left : var(--bs-gutter-x,.75rem);
margin-right: auto;
margin-left : auto;
} 
.p-2{
	padding : 30px !important;
}
.nav-scroller{
	position: relative;
	z-index: 2;
	height: 2.75rem;
	overflow-y : 	hidden;
}

</script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

	
		<!--화면 메인 네비게이션바 -->
		<header class="main-header py-3">
			<nav class="navbar navbar-expand-sm navbar-white bg-white">
				<div class="container-fluid">
					<a class="navbar-brand" href="#">Logo</a>
					<button class="navbar-toggler" type="button"
						data-bs-toggle="collapse" data-bs-target="#mynavbar">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="mynavbar">
						<ul class="navbar-nav ms-auto">
							<li class="nav-item"><a class="nav-link" id="link1" href="../../login.jsp">로그인</a>
							</li>
							<li class="nav-item"><a class="nav-link" id="link2" href="../../joinmember.jsp">회원가입</a>
							</li>
							<li class="nav-item">
								<button type="button" class="btn btn-primary dropdown-toggle"
									data-bs-toggle="dropdown">글쓰기</button>
								<ul class="dropdown-menu dropdown-menu-end"
									aria-labelledby="navbarDropdown">
									<li><a class="dropdown-item" href="#">가구</a></li>
									<li><a class="dropdown-item" href="#">디지털</a></li>
									<li><a class="dropdown-item" href="#">생활용품</a></li>
									<li><a class="dropdown-item" href="#">식물</a></li>
								</ul>
							</li>

						</ul>
					</div>
				</div>
			</nav>
		
		<hr>
		<!-- 보조 메뉴 바 -->
		<div class="nav-scroller py-1 mb-2">
			<nav class="nav d-flex justify-content-start">
				<a class="p-2 link-secondary" href="#"><img src="#">가구</a> <a
					class="p-2 link-secondary" href="#"><img src="#">디지털</a> <a
					class="p-2 link-secondary" href="#"><img src="#">생활용품</a> <a
					class="p-2 link-secondary" href="#"><img src="#">식물</a>
			</nav>

		</div>
		</header>
		<main>
		<div class="container">
		<!-- 앨범내역 -->
		<div class="album py-5 bg-light">
			<div class="container">
				<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
					<div class="col">
						<div class="card shadow-sm">
							<img src="#" href="#" width="100%" height="225">
							<div class="card-body">
								<p class="card-text">
									msg1
							</div>
						</div>
					</div>
					<div class="col">
						<div class="card shadow-sm">
							<img src="#" href="#" width="100%" height="225">
							<div class="card-body">
								<p class="card-text">
									msg2
							</div>
						</div>
					</div>
					<div class="col">
						<div class="card shadow-sm">
							<img src="#" href="#" width="100%" height="225">
							<div class="card-body">
								<p class="card-text">
									msg3
							</div>
						</div>
					</div>
					 <div class="col">
						<div class="card shadow-sm">
							<img src="#" href="#" width="100%" height="225">
							<div class="card-body">
								<p class="card-text">
									msg4
							</div>
						</div>
					</div>
					<div class="col">
						<div class="card shadow-sm">
							<img src="#" href="#" width="100%" height="225">
							<div class="card-body">
								<p class="card-text">
									msg5
							</div>
						</div>
					</div>
					<div class="col">
						<div class="card shadow-sm">
							<img src="#" href="#" width="100%" height="225">
							<div class="card-body">
								<p class="card-text">
									msg6
							</div>
						</div>
					</div>
					<div class="col">
						<div class="card shadow-sm">
							<img src="#" href="#" width="100%" height="225">
							<div class="card-body">
								<p class="card-text">
									msg7
							</div>
						</div>
					</div>
					<div class="col">
						<div class="card shadow-sm">
							<img src="#" href="#" width="100%" height="225">
							<div class="card-body">
								<p class="card-text">
									msg8
							</div>
						</div>
					</div>
					<div class="col">
						<div class="card shadow-sm">
							<img src="#" href="#" width="100%" height="225">
							<div class="card-body">
								<p class="card-text">
									msg9
							</div>
						</div>
					</div>
					<div class="col">
						<div class="card shadow-sm">
							<img src="#" href="#" width="100%" height="225">
							<div class="card-body">
								<p class="card-text">
									msg10
							</div>
						</div>
					</div>
					<div class="col">
						<div class="card shadow-sm">
							<img src="#" href="#" width="100%" height="225">
							<div class="card-body">
								<p class="card-text">
									msg12
							</div>
						</div>
					</div>
					<div class="col">
						<div class="card shadow-sm">
							<img src="#" href="#" width="100%" height="225">
							<div class="card-body">
								<p class="card-text">
									msg12
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</main>
	<footer class="text-muted py-5">
		<div class="container">
			<p class="float-end mb-1">
				<a href="#">이미지 </a>
			</p>
			<p>내용들들들</p>
			<p class="mb-0">
				msg1
				<a href="#"></a>
				<img src="#">
			</p>
			
		</div>
	</footer>

</body>
</html>