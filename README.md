# BEN-OUADDAY_Alya

Bonjour, voici mon projet. 

Pour réaliser une architecture mvc, il aurait fallu faire une classe modele. J'aurais abonné tous les composants de la vue (avec la balise fxml)
a un evenement qui suppose que dès que les valeurs sont changee (ici tentative de dessin, de changement de couleur, de clonage ou de suppression) 
notifie immédiatement la classe modele. 
Cette dernière aura une fonction valueChanged() qui va ordonner a la vue (ici une deuxieme partie du controller car fichier FXML) de 
s'update. La fonction updateViews() du controlleur se chargera alors d'effectivement creer les formes, de changer les couleurs, de cloner
et supprimer en fonction d'evenement qui a cause le valueChanged 
