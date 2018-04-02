<html>

<head>

	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0"> <!-- Página Responsive  -->

</head>
<body>

	<h1 style="color:blue">URJC-ACADEMY</h1>
	<h2 style="color:blue">ROOT SESSION</h2>
	
	<h3 style="color:green">Titulaciones</h3>
	
	<ul>
		
		${{#titulaciones}}
		<li><a href="/root/titulacion/{{id}}">{{nombre}}</a></li>
		${{/titulaciones}}
	
	</ul>
	
</body>

</html>