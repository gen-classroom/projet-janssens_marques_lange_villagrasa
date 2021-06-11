# Static Website Generator

Static website generator made for the GEN course at HEIG-VD.
## Authors
* Fabio Marques		- fabio10000
* Yanik Lange		- yanik23
* Diego Villagrasa	- TheSnekySnek
* Emmanuel Janssens	- EmmanuelJanssens

## Description

## Installation
Make sure to have `Java 11` or higher.
 - Clone this repository
 ```bash
 git clone https://github.com/gen-classroom/projet-janssens_marques_lange_villagrasa.git
 cd projet-janssens_marques_lange_villagrasa/my-app
 ```
 - Compile the project
  ```bash
 maven clean
 maven install
 ```
 ## Usage

 ### Create a new site
 Due to an issue with maven not copying the `resources` folder to the target directory, we are unable to `init` a site at the end of this sprint.
 
 You can still use the other commands on the `websites/mysite` website or use intellij to use the init command.
 
 Creates a new website with the default pages
 ```bash
 java -cp target/lib/*:target/my-app-0.0.1-SNAPSHOT.jar com.gen.app.Statique init websites/mywebsite
 ```
 
 ### Build the website
 Builds the specified site
 ```bash
 java -cp target/lib/*:target/my-app-0.0.1-SNAPSHOT.jar com.gen.app.Statique build websites/mywebsite
 ```
 You can also add the -watch argument to trach file changes and automatically recompile the pages
 
 ### Serve the website
 Serves the site on the user's web browser
 ```bash
 java -cp target/lib/*:target/my-app-0.0.1-SNAPSHOT.jar com.gen.app.Statique serve websites/mywebsite
 ```
 You can also add the -watch argument to trach file changes and automatically recompile the pages
 
 ### Clean the website
 Deletes the build of the website
 ```bash
 java -cp target/lib/*:target/my-app-0.0.1-SNAPSHOT.jar com.gen.app.Statique clean websites/mywebsite
 ```

### Dependencies
* Java 11+
* Maven
* Picocli
* Handlebars
* Txtmark


