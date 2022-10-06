import Navbar from "./Navbar";
import {ApplicationApi, Configuration, ProjectSo, UserSo} from "../openapi";
import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {Button, Col, Container, Dropdown, Row, Table} from "react-bootstrap";
import Form from 'react-bootstrap/Form';

const conf = new Configuration({
    basePath: 'http://localhost:3000/api/findev',
});

const api = new ApplicationApi(conf);

const ProjectInfo = () => {

    const {id} = useParams();
    let idInt: number =+ id;

    const [project, setProject] = useState<ProjectSo>();
    const [users, setUsers] = useState<UserSo[]>([]);

    const addUserHandler: any = (userId) => {
        api.setProject(userId, idInt).then(() => {
            api.getProjectById(idInt)
                .then((data) => {
                    setProject(data);
                })
                .catch((error) => {
                    console.log(error);
                });
        });

    }

    const removeUserHandler: any = (userId) => {
        api.removeProject(userId, idInt).then(() => {
            api.getProjectById(idInt)
                .then((data) => {
                    setProject(data);
                })
                .catch((error) => {
                    console.log(error);
                });
        });

    }

    useEffect(() => {
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
        console.log("bug");
    }, [id]);

    if (typeof project === 'undefined') return (
        <div>
            <Navbar/>
            <p>Project not found</p>
        </div>
    )

    return(
        <div>
            <Navbar/>
            <div className="w-50 d-inline-block">
                <p className="fw-bold">Title: {project.title}</p>

                <p className="font-monospace">Description: {project.description}</p>
                <p>ID #{project.id}</p>
            </div>
            <div className="w-50 d-inline-block">
                <Form.Group controlId="formFile" className="m-3">
                    <Form.Label>Change the image for this project</Form.Label>
                    <Form.Control type="file" />
                </Form.Group>
                <Button className="m-3" variant="danger">Delete Project</Button>
                <Dropdown className="m-3">
                    <Dropdown.Toggle id="dropdown-button-dark-example1" variant="primary">
                        Add New User For This Project
                    </Dropdown.Toggle>

                    <Dropdown.Menu variant="dark">
                        {users.map((user) => (
                            <Dropdown.Item key = {user.id} onClick={ event => addUserHandler(user.id)} >
                                {user.id} {user.name} {user.surname}
                            </Dropdown.Item>
                        ))}
                    </Dropdown.Menu>
                </Dropdown>
            </div>
            <Container>
                <Row>
                    <Col className="fw-bold w-50">Users: </Col>
                    <Col>

                    </Col>
                </Row>
            </Container>
            <Table striped bordered hover size="sm" className="w-50">
                <thead>
                <tr>
                    <th>#</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                {Array.from(project.users).map((user) => (
                    <tr key = {user.id}>
                        <td>{user.id}</td>
                        <td>{user.name}</td>
                        <td>{user.surname}</td>
                        <td><Button onClick={event => removeUserHandler(user.id)} variant="danger">Remove User</Button></td>
                    </tr>
                ))}
                </tbody>
            </Table>
        </div>
        )
}

export default ProjectInfo;