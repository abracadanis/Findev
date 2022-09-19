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
        api.getProjectById((id as unknown) as number).then((data) => {
            setProject(data);
            console.log(data);
        });
    }, []);

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