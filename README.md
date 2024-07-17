
# Crud & Login Vaadin Project

This is a Crud Students and Login application using Java, Vaadin and Spring Boot Framework. 
So, in the application you can do registration and login with different roles of users who have different permissions in the application.

The 2 roles are:

    1. USER (Create and Load students).
    2. ADMIN (Create, Load and Delete students).

This Project use MySQL to save the data into the database
## Demo

#### LOGIN
![LOGIN](https://i.ibb.co/WyqFG3G/Captura-de-pantalla-2024-07-16-193541.png)
#### SIGN UP 
![SIGNUP](https://i.ibb.co/hLLdWpM/Captura-de-pantalla-2024-07-16-194525.png)
#### MAIN VIEW
![main](https://i.ibb.co/hWW7qgW/Captura-de-pantalla-2024-07-16-195214.png)
#### CREATE VIEW 
![CREATE](https://i.ibb.co/sP26LPd/Captura-de-pantalla-2024-07-16-195227.png)
#### DELETE VIEW
![LOGIN](https://i.ibb.co/k37Pcvz/Captura-de-pantalla-2024-07-16-195242.png)
#### ERROR MESSAGE FOR DELETE VIEW 
![SIGNUP](https://i.ibb.co/DVV2CSG/Captura-de-pantalla-2024-07-16-195302.png)



## Installation

First, create a new database with the  name 'udemyvaadin' or change this name in the file application.properties. 

Then, you can run the application with this command in your cmd

```bash
  mvn spring-boot:run
```
This project use the port 8080 as default, so if you want to run the application in other port, add this code into the file application.properties

```bash
    server.port=****
```
 
However, if you want to use the same data that you can see in the Demo, you can add this code in your database. 
You can use this users to do login
- username: admin password: admin (ADMIN role)
- username: diego password: user  (USER role)

```sql
--
-- Base de datos: `udemyvaadin`
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `role`
--

CREATE TABLE `role` (
  `id_role` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `role`
--

INSERT INTO `role` (`id_role`, `name`) VALUES
(1, 'USER'),
(2, 'ADMIN');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `status`
--

CREATE TABLE `status` (
  `id_status` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `status`
--

INSERT INTO `status` (`id_status`, `name`) VALUES
(1, 'ACTIVE'),
(2, 'PASSIVE'),
(3, 'ABSOLVED');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `student`
--

CREATE TABLE `student` (
  `id_student` int(11) NOT NULL,
  `age` int(11) NOT NULL,
  `country` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `zipcode` int(11) NOT NULL,
  `id_status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `student`
--

INSERT INTO `student` (`id_student`, `age`, `country`, `name`, `zipcode`, `id_status`) VALUES
(1, 25, 'USA', 'DIEGO', 1235, 1),
(2, 22, 'USA', 'John', 1358, 1),
(3, 38, 'Spain', 'Juanito', 4444, 2),
(4, 28, 'Spain', 'Midu', 1234, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `id_role` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id`, `password`, `username`, `id_role`) VALUES
(2, '$2a$10$vViFmjVGrJTcsdhs3o.ozuQ0h99.jYecxKIZvN4oLw.RnZdfIhxW6', 'diego', 1),
(4, '$2a$10$u2mycW8SwIGedCctwyhIJ.iiiMKTCbpVi23qZsQ1S0tWu3QwHn0MK', 'admin', 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id_role`);

--
-- Indices de la tabla `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`id_status`);

--
-- Indices de la tabla `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id_student`),
  ADD KEY `FKde5qeyhha129u3mh1wrxb6gf2` (`id_status`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6njoh3pti5jnlkowken3r8ttn` (`id_role`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `role`
--
ALTER TABLE `role`
  MODIFY `id_role` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `status`
--
ALTER TABLE `status`
  MODIFY `id_status` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `student`
--
ALTER TABLE `student`
  MODIFY `id_student` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `FKde5qeyhha129u3mh1wrxb6gf2` FOREIGN KEY (`id_status`) REFERENCES `status` (`id_status`);

--
-- Filtros para la tabla `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FK6njoh3pti5jnlkowken3r8ttn` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

```
## Documentation

- https://vaadin.com/docs/latest/flow/security/enabling-security
- https://spring.io/guides/gs/accessing-data-mysql
- https://spring.io/projects/spring-security

