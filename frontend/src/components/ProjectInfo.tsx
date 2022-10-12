import Navbar from "./Navbar";
import {ApplicationApi, Configuration, ProjectSo, UserSo} from "../openapi";
import {redirect, useNavigate, useParams} from "react-router-dom";
import {ChangeEvent, useEffect, useState} from "react";
import {Button, Col, Container, Dropdown, ListGroup, Modal, Row, Stack, Table} from "react-bootstrap";
import Form from 'react-bootstrap/Form';
import "./ProjectInfo.css";

const conf = new Configuration({
    basePath: 'http://localhost:3000/api/findev',
});

const api = new ApplicationApi(conf);

const ProjectInfo = () => {

    const {id} = useParams();
    let idInt: number =+ id;

    const navigate = useNavigate();

    const [project, setProject] = useState<ProjectSo>();
    const [users, setUsers] = useState<UserSo[]>([]);
    const [showPopUp, setShowPopUp] = useState(false);
    const [imageBlob, setImageBlob] = useState<Blob | undefined>();
    const [imageURL, setImageURL] = useState<string | undefined>();

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

    const onFileChanged = (event: ChangeEvent<HTMLInputElement>) => {
        api.getImage(project.imageId)
            .then(blob => setImageURL(URL.createObjectURL(blob)));
        if (event.target.files && event.target.files.length) {
           setImageBlob( event.target.files[0]);
        }
    };

    const saveImage = () => {
        api.setImage(project.id, imageBlob).then(() => {
                api.getProjectById(idInt)
                    .then((data) => {
                        setProject(data);
                    })
                    .catch((error) => {
                        console.log(error);
                    })}
        );
    }

    const deleteProject = () => {
        api.deleteProject(project.id)
            .then(() => {
                navigate("/projects/");
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

    useEffect(() => {
        if(project?.imageId) {
            api.getImage(project.imageId)
                .then(blob => {
                    setImageURL(URL.createObjectURL(blob))
                })
        }
        console.log("bug2");
    }, [project])

    if (typeof project === 'undefined') return (
        <div>
            <Navbar/>
            <p>Project not found</p>
        </div>
    )

    return(
        <div>
            <Navbar/>
            <br/>
            <br/>
            <div className="d-inline-flex align-items-start">
                <div className="w-50 d-inline-block m-3">
                    <p className="fw-bold">Title: {project.title}</p>
                    {project.imageId && <img src={imageURL} className="w-100 d-flex justify-content-center"/>}
                    <p className="font-monospace">Description: {project.description}</p>
                    <p>ID #{project.id}</p>
                </div>
                <div className="w-50 d-inline-block mt-0 align-self-baseline">
                    <Form.Group controlId="formFile" className="m-3">
                        <Button className="d-inline-flex" variant="primary" onClick={() => setShowPopUp(!showPopUp)}>Change the image for this project</Button>
                        {showPopUp &&
                            <Modal.Dialog>
                                <Modal.Header >
                                    <Modal.Title>Change the image for this project</Modal.Title>
                                </Modal.Header>

                                <Modal.Body>
                                    <Form.Control type="file" onChange={onFileChanged} />
                                </Modal.Body>

                                <Modal.Footer>
                                    <Button variant="secondary" onClick={() => setShowPopUp(false)}>Close</Button>
                                    <Button variant="primary" onClick={() => saveImage()}>Save image</Button>
                                </Modal.Footer>
                            </Modal.Dialog>}
                    </Form.Group>
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
                    <Button className="m-3" variant="danger" onClick={event => {deleteProject()}}>Delete Project</Button>
                </div>
            </div>

            <Container>
                <Row>
                    <Col className="fw-bold w-50">Users: </Col>
                    <Col>

                    </Col>
                </Row>
            </Container>
            <ListGroup className="w-50">
                    {Array.from(project.users).map((user) => (
                        <ListGroup.Item key={user.id} action className="m-3" href={"/users/" + user.id}><div className="d-inline-flex w-75">{user.name} {user.surname}</div> <Button onClick={event => removeUserHandler(user.id)} variant="danger">Remove User</Button></ListGroup.Item>
                    ))}
            </ListGroup>
        </div>
        )
}

export default ProjectInfo;