<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Get Note System</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="javascript.js"></script>
</head>
<body>
<%@ page import="java.util.*,br.tech.*"%>

	<div class="navbar">
		<form method="POST"	action=" ${pageContext.request.contextPath}/adiciona">
			<table border="1px">
				<tr>
					<td><textarea id="msgIn" name="msgIn" type="text"
						placeholder="Adicione sua nota aqui...." cols="" rows=""></textarea></td>
					<td><textarea id="tagIn" name="tagIn"
						placeholder="Tags (Usar espaço entre cada tag)"
						cols="" rows=""></textarea></td>
					<td><button type="submit">Adicionar</button></td>
				</tr>
			</table>
		</form>
	</div>


	<div id="spacer"></div>
	<form action="${pageContext.request.contextPath}/BuscaServlet" method="POST">
			<table border="1px" id="buscaBar">
				<tr>
					<td><input type="text" placeholder="Busca por Tag" id="tagBusca" name="tagBusca"></td>
					<td><input type="submit" value="Buscar"></td>
				</tr>
			</table>
		</form>
	<table border="1" class="listaNotas">
		<tr>
			<td class="iddoTagBar"><h2>Id</h2></td>
			<td class="noteTagbar"><h2>Notas</h2></td>
			<td class="tagsTagbar"><h2>Tags</h2></td>
			<td class="editTagbar"><h2>Editar</h2></td>
			<td class="editTagbar"><h2>Deletar</h2></td>
		</tr>
		<%	DAO dao = new DAO();
			List<Integer> listaId = (List<Integer>) request.getAttribute("result");
			List<Mensagem> messages = dao.getListaBusca(listaId);
			for (Mensagem mensagem : messages) {
				String asd = "";
				for (String str : mensagem.getTag()) {
					asd += str + " ";
				}
				String msgstr = mensagem.getMens();
				int id = mensagem.getId();
								
		%>
		<tr>
			<td><%=mensagem.getId()%></td>
			<td><%=mensagem.getMens()%></td>
			<td><%=asd%></td>
			<td><button onClick="onEditID(<%=mensagem.getId()%>);onEditMsg('<%=mensagem.getMens()%>');onEditTag('<%=asd%>');">Edit</button></td>
			<td><button onclick="onDel(<%=mensagem.getId()%>)">Delete</button></td>
		</tr>
		<%
			}
		%>
	</table>


	<div id="overlayEdit">
		<div id="textOverAlt">

			<form action=" ${pageContext.request.contextPath}/altera" method="POST">
				<table>
					<tr>
						<td>Tem certeza que deseja alterar essa nota?</td>
					</tr>
					<tr>
						<td>Notas:</td>
						<td><textarea type="text" name="msgEdit" id="msgEdit"></textarea>
						<input type="hidden" name="idAlterar" id="idAlterar"></td>
					</tr>
					<tr>
						<td>Tags:</td>
						<td><textarea type="text" name="tagEdit" id="tagEdit"></textarea></td>
					</tr>
					<tr>
						<td><input type="submit"></td>
					</tr>
				</table>
			</form>
			<table>
				<tr>
					<td><button onclick="offEdit()">Cancela</button></td>
				</tr>
			</table>
		</div>
	</div>
	<div id="overlayDelete">

		<table id="textOverDel">
			<tr>
				<td>Tem certeza que desejas apagar?</td>
				<td>
					<form method="POST"
						action="${pageContext.request.contextPath}/remove">
						<input type="hidden" name="idApagar" id="qualApagar"> <input
							type="submit" value="Apagar">
					</form>
				</td>
				<td>
					<button onclick="offDel()">Cancela</button>
				</td>
			</tr>
		</table>
	</div>

</body>
</html>