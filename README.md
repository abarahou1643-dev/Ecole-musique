--------------- Description du projet-----------------


2. Contexte fonctionnel
École de Musique "Harmonie" - Application de gestion pour une école de musique proposant des cours instrumentaux avec différents professeurs spécialisés.

3.Objectif de l'application
Digitaliser la gestion des ressources pédagogiques (instruments, professeurs) et optimiser la planification des cours de musique.

4.Public cible / Cas d'usage
Administrateurs de l'école pour la gestion des ressources

Secrétaires pour l'organisation des emplois du temps

Directeurs pour le suivi des statistiques et indicateurs

5.Fonctionnalité principale
"Gérer efficacement les instruments disponibles, les professeurs spécialisés et planifier les cours de musique avec suivi statistique."

------------------Architecture technique-------------------


1 Stack technologique
Backend : Spring Boot 3.5.7, Spring Data JPA/Hibernate 6.6.33

Frontend : Thymeleaf 3.1.3, HTML5, CSS3, Bootstrap 5.1.3

Base de données : MySQL 5.5.5

Build : Gradle


2. Structure du code :

<img width="944" height="421" alt="cccccccc" src="https://github.com/user-attachments/assets/9de14711-55d5-46bf-b8a2-351fa139fc9c" />




3. Diagramme d'architecture :

   

<img width="506" height="107" alt="11111111111111111111" src="https://github.com/user-attachments/assets/acd73701-22d9-4a16-bb8b-e77b190c773f" />



4. Fonctionnalités principales :
   
+CRUD Complet 
   Instruments : Ajouter/modifier/supprimer/consulter les instruments
   
   Professeurs : Gérer les professeurs et leurs spécialités
   
   Cours : Planifier les cours (jour, heure, durée, niveau)

   
+Recherche/Filtrage 

  Cours : Par niveau (Débutant/Intermédiaire/Avancé) et par jour
  
  Instruments : Par famille (Cordes/Vents/Claviers/Percussions) et disponibilité
  
  Professeurs : Par spécialité (Piano/Guitare/Violon/etc.)
  
+Tableau de bord statistiques 

  Cours par jour : Répartition hebdomadaire
  
  Popularité instruments : Classement d'utilisation
  
  Niveaux enseignés : Répartition pédagogique
  
+Gestion des statuts 

   Instruments : Disponible/Indisponible
   
   Professeurs : Actif/Inactif
   
   Cours : Programmé/Complet/Annulé (via capacité)

  5.Modèle de données :

 1 >Entités principales
 
+Instrument

java

@Entity

public class Instrument {

    private String nom;          // PK
    
    private String famille;      // Cordes/Vents/Claviers/Percussions
    
    private Boolean disponibilite;
    
    private LocalDateTime dateCreation;
}
+Prof

java

@Entity 

public class Prof {

    private String nom;          // PK
    
    private String specialite;   // Piano/Guitare/Violon/Batterie...
    
    private String email;
    
    private Boolean actif;
    
    private LocalDate dateEmbauche;
}

+Cours

java

@Entity

@IdClass(CoursId.class)

public class Cours {

    private String jour;         // PK composite
    
    private LocalTime heureDebut; // PK composite  
    
    private Prof professeur;     // PK composite + FK
    
    private Integer dureeMin;
    
    private String niveau;       // Débutant/Intermédiaire/Avancé
    
    private Instrument instrument; // FK optionnel
    
    private String salle;
    
    private BigDecimal prix;
    
    private Integer placesMax;
}

2 > Relations

Cours → Professeur : @ManyToOne (Un cours = un professeur, un professeur = plusieurs cours)


Cours → Instrument : @ManyToOne (Un cours peut utiliser un instrument, un instrument = plusieurs cours)


Clé composite : Cours identifié par (jour + heureDebut + professeur)

3 > Configuration base de données
properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecole_musique
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update

6. Lancer le projet :

1 > Prérequis

Java : JDK 21

MySQL : 5.5.5+

Gradle : Inclus dans le wrapper

2 >Installation 

# Cloner le projet

git clone [repository]

cd ecole-musique

# Configurer la base (exécuter le script SQL fourni)

mysql -u root -p < database_setup.sql

# Lancer l'application

./gradlew bootRun

# ou

mvn spring-boot:run


3 > Accès


Application : http://localhost:8080

Tableau de bord : http://localhost:8080/cours/stats

Gestion instruments : http://localhost:8080/instruments

Gestion professeurs : http://localhost:8080/profs

Gestion cours : http://localhost:8080/cours


----------------------------------Démonstration----------------------------------------------------------------------




https://github.com/user-attachments/assets/1f88fef0-5e4c-4575-bd89-da2f97b79a32




  <img width="959" height="507" alt="CV1" src="https://github.com/user-attachments/assets/d81410e6-e3ff-4a47-b048-8bd2d01f7bba" />




https://github.com/user-attachments/assets/055dd921-2444-49d5-9225-0aedb8b19ddc





https://github.com/user-attachments/assets/9f9d2f0e-38a9-48e0-803c-23f9cb68dc8f





1. Liste des Instruments 


Filtres de recherche:



https://github.com/user-attachments/assets/4343cc71-0c60-449f-80f7-ce00ed79dca2


Ajouter un nouvel instrument:


https://github.com/user-attachments/assets/20a9bcb1-a7cd-40f9-826c-2eaaac7cf75d


2. Liste des Professeurs

   Filtres de recherche:
   

https://github.com/user-attachments/assets/91063e34-eee4-4b6d-9062-40d3e1d42b4c

  Ajouter un nouveau professeur :
  
   
https://github.com/user-attachments/assets/ea71bcba-6f29-490d-9385-59ecdf690df4

3.Liste des Cours 

   Filtres de recherche:
   

https://github.com/user-attachments/assets/6474e595-65a0-4ec5-8110-ef4305cab8c7

 Ajouter un nouveau cours :


https://github.com/user-attachments/assets/f7e5db62-a113-4d71-9c27-68e2b598c92b


 4. Tableau de Bord - Statistiques

    

https://github.com/user-attachments/assets/f79ad9b6-9a15-43c5-9294-2f35beec785f


Auteurs / Encadrement
Étudiant 
Nom :aicha barahou
Encadrement : Mohamed lachgar
Module : Programmation Avancées et DevOps
Établissement :ENS-marrakech
Année : 2025


 





