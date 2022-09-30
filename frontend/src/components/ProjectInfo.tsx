import Navbar from "./Navbar";
import {ApplicationApi, Configuration, ProjectSo, UserSo} from "../openapi";
import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {Button, Col, Container, Dropdown, Row, Table} from "react-bootstrap";

const conf = new Configuration({
    basePath: 'http://localhost:3000/api/findev',
});

const api = new ApplicationApi(conf);

const ProjectInfo = () => {

    const {id} = useParams();
    let idInt: number =+ id

    const [project, setProject] = useState<ProjectSo>();
    const [users, setUsers] = useState<UserSo[]>();

    useEffect(() => {
        console.log(idInt);
        console.log(typeof idInt);
        api.getProjectById(idInt)
            .then((data) => {
            setProject(data);
        })
            .catch((error) => {
                console.log(error);
            });

        api.getUsers().then((getusers) => {
            setUsers(getusers);
        })
    }, [id]);

    const addUserHandler: any = (userId) => {
        api.setProject(userId, idInt).then();
        api.getProjectById(idInt)
            .then((data) => {
                setProject(data);
            })
    }

    if (typeof project === 'undefined') return (
        <div>
            <Navbar/>
            <p>Project not found</p>
        </div>
    )

    return(
        <div>
            <Navbar/>
            <p className="fw-bold">Title: {project.title}</p>
            <p className="font-monospace">Description: {project.description}</p>
            <p>ID #{project.id}</p>
            <Container>
                <Row>
                    <Col className="fw-bold w-50">Users: </Col>
                    <Col>
                        <Dropdown>
                            <Dropdown.Toggle id="dropdown-button-dark-example1" variant="secondary">
                                Add New User For This Project
                            </Dropdown.Toggle>

                            <Dropdown.Menu variant="dark">
                                {users.map((user) => (
                                    <Dropdown.Item key = {user.id} onClick={addUserHandler(user.id)}>
                                        {user.id} {user.name} {user.surname}
                                    </Dropdown.Item>
                                ))}
                            </Dropdown.Menu>
                        </Dropdown>
                    </Col>
                </Row>
            </Container>
            <Table striped bordered hover size="sm" className="w-50">
                <thead>
                <tr>
                    <th>#</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Username</th>
                </tr>
                </thead>
                <tbody>
                {Array.from(project.users).map((user) => (
                    <tr>
                        <td>{user.id}</td>
                        <td>{user.name}</td>
                        <td>{user.surname}</td>
                    </tr>
                ))}
                </tbody>
            </Table>
        </div>
        )
}

export default ProjectInfo;