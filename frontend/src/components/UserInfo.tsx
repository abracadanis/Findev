import Navbar from "./Navbar";
import {ApplicationApi, Configuration, UserSo} from "../openapi";
import {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import {Button, ListGroup, Table} from "react-bootstrap";

const conf = new Configuration({
    basePath: 'http://localhost:3000/api/findev',
});

const api = new ApplicationApi(conf);

const UserInfo = () => {

    const {id} = useParams();
    let idInt: number =+ id;

    const [user, setUser] = useState<UserSo | undefined>();

    const removeProjectHandler: any = (projectId) => {
        api.removeProject(idInt, projectId).then(() => {
            api.getUserById(idInt)
                .then((data) => {
                    setUser(data);
                })
                .catch((error) => {
                    console.log(error);
                });
        });

    }

    useEffect(() => {
        api.getUserById(idInt)
            .then((data) => {
                console.log(data);
                setUser(data);
            })
            .catch((error) => {
                console.log(error);
            })
    }, [id]);

    if (typeof user === 'undefined') return (
        <div>
            <Navbar/>
            <p>User not found</p>
        </div>
    )

    return (
        <div>
            <Navbar/>
            <div className="m-3">User #{user.id} <p>{user.name} {user.surname}</p></div>
            <p className="m-3">Attached projects: </p>

            <ListGroup className="w-50 m-3">
                {Array.from(user.projects).map((project) => (
                    <ListGroup.Item key={project.id} action href={"/projects/" + project.id} ><div className="d-inline-flex w-75">{project.title}</div> <Button onClick={event => removeProjectHandler(project.id)} variant="danger">Remove Project</Button></ListGroup.Item>
                    ))}
            </ListGroup>

        </div>
    )
}

export default UserInfo;