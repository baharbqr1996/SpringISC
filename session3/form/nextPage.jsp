<html>
   <head>
      <title>welcome</title>
   </head>
   
   <body>
      <center>
      <h1>your information:</h1>
      
      <ul>
         <li><p><b>First Name:</b>
            <%= request.getParameter("first_name")%>
         </p></li>
         <li><p><b>Last  Name:</b>
            <%= request.getParameter("last_name")%>
         </p></li>
		 <li><p><b>Date Of Birth:</b>
		             <%= request.getParameter("date_of_birth")%>
		 </p></li>
      </ul>
   </body>
</html>