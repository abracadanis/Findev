import Navbar from "./components/Navbar";
import React, {useEffect, useState} from "react";
import ProjectList from "./components/ProjectList/ProjectList";
import NewProjectForm from "./components/NewProject/NewProjectForm";
import {ProjectSo, UserSo} from "./openapi";
import useApiHook from "./hooks/useApiHook";

const ProjectsPage = () => {

    const api = useApiHook();

    const [projects, setProjects] = useState<ProjectSo[]>([]);
    const [users, setUsers] = useState<UserSo[]>([]);

    useEffect(() => {
        api.getProjects().then((data) => {
            setProjects(data);
        });
        api.getUsers().then((usersData) => {
            setUsers(usersData);
        });
    }, [])

    const handleUpdate = () => {
        api.getUsers().then((usersData) => {
            setUsers(usersData);
        })
        api.getProjects().then((data) => {
            setProjects(data);
        });
    }

    return (
        <div>
            <Navbar/>
            <div className="m-4 p-3 bg-dark rounded w-50">
                <NewProjectForm users={users} handleUpdate={handleUpdate}/>
            </div>
            <ProjectList projects={projects}/>
        </div>
    )
}

export default ProjectsPage;