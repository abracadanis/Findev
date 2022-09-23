import Navbar from "./Navbar";
import {ApplicationApi, Configuration, ProjectSo} from "../openapi";
import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";

const conf = new Configuration({
    basePath: 'http://localhost:3000/api/findev',
});

const api = new ApplicationApi(conf);

const ProjectInfo = () => {

    const {id} = useParams();

    const [project, setProject] = useState<ProjectSo>();

    useEffect(() => {
        let idInt: number =+ id;
        console.log(idInt);
        console.log(typeof idInt);
        api.getProjectById(idInt)
            .then((data) => {
            setProject(data);
            console.log("project = " + project);
        })
            .catch((error) => {
                console.log(error);
            });
    }, [id]);

    if (typeof project === 'undefined') return (
        <div>
            <Navbar/>
            <p>ERROR</p>
        </div>
    )

    return(
        <div>
            <Navbar/>
            <p>{project.title}</p>
            <p>{project.description}</p>
            <p>{project.id}</p>

        </div>
        )
}

export default ProjectInfo;