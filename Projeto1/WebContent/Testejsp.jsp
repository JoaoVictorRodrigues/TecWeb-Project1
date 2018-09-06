<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="jscript.js"></script>
<meta charset="ISO-8859-1">
<title>Get Notes System</title>
</head>
<body>
	<%@ page import="java.util.*,br.tech.*"%>

	<div class="navbar">
		<table border="1px">
			<tr>
				<td><textarea id="msgIn" name="msgIn"
						placeholder="Adicione sua nota aqui...." cols="" rows=""></textarea></td>
				<td><textarea id="tagIn" name="tagIn"
						placeholder="Tags (usar o '#' no comeco de cada tag e separar as tags com espaço)"
						cols="" rows=""></textarea></td>
				<td>
					<button autofocus="autofocus">Adicicionar mensagem</button>
				</td>
			</tr>
		</table>
	</div>


	<div id="spacer"></div>
	<table border="1" class="listaNotas">
		<tr>
			<td class="iddoTagBar"><h2>Id</h2></td>
			<td class="noteTagbar"><h2>Notas</h2></td>
			<td class="tagsTagbar"><h2>Tags</h2></td>
			<td class="editTagbar"><h2>Editar</h2></td>
			<td class="editTagbar"><h2>Deletar</h2></td>
		</tr>
		<%
			DAO dao = new DAO();
			List<Mensagem> messages = dao.getLista();
			for (Mensagem mensagem : messages) {
				String asd = "";
				for (String str : mensagem.getTag()) {
					asd += str + "\n";
				}
		%>
		<tr>
			<td><%=mensagem.getId()%></td>
			<td><%=mensagem.getMens()%></td>
			<td><%=asd%></td>
			<td><button onClick="onEdit()">Edit</button></td>
			<td><button onclick="onDel(<%=mensagem.getId()%>)">Delete</button></td>
		</tr>
		<%
			}
		%>
	</table>


	<div id="overlayEdit">
		<div id="textOverAlt">
			<form action="altera">
				<table>
					<tr>
						<td>Tem certeza que deseja alterar essa nota?</td>
					</tr>
					<tr>
						<td>Notas:</td>
						<td><input type="text" name="msgEdit"></td>
					</tr>
					<tr>
						<td>Tags:</td>
						<td><input type="text" name="tagEdit"></td>
					</tr>
					<tr>
						<td><input type="submit"></td>
						<td><button onclick="offEdit()">Cancela</button></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div id="overlayDelete">

		<table id="textOverDel">
				<tr>
				<td>Tem certeza que desejas apagar?</td>
				<td>
					<button>Apagar</button>
				</td>
				<td>
					<button onclick="offDel()">Cancela</button>
				</td>
			</tr>
		</table>
	</div>





</body>
</html>