Clever Cloud Credential


Cuando se procede al despliege de la aplicacion, al inicalizar la app con el enlace
http://app-aca80880-042d-4133-b29f-6444ac32ea4e.cleverapps.io o https://acme-planer-g18-sp2.cleverapps.io
no se encuentra la app y eso es porque se ha modificado el war.json para poder posibilidad el rol manager de 
manajar la app sin problema por lo que podra acceder a la app con las siguientes URL:
http://app-aca80880-042d-4133-b29f-6444ac32ea4e.cleverapps.io/Acme-Planner 
https://acme-planer-g18-sp2.cleverapps.io/Acme-Planner
Todo esto es debido a que el context por defecto del proyecto venia especificado como "/",
y cunado accedia un manager a la app se rompia por el tomcat, por lo que al retocarse el war.json se modifico el 
context a "/Acme-Planer
