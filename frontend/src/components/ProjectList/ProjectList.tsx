import {ProjectSo, UserSo} from '../../openapi';
import ProjectItem from './ProjectItem';
import React from "react";
import NewProjectForm from "../NewProject/NewProjectForm";

interface Props {
    project: ProjectSo[],
    user: UserSo[];
}

const ProjectList = (props: Props) => {

    return (
        <div>
            <div className="m-4 p-3 bg-dark rounded w-50">
                <NewProjectForm user={props.user}/>
            </div>
            <div className='d-flex justify-content-around flex-wrap'>
                {props.project.map((project) => (
                    <ProjectItem
                        key={project.title}
                        {...project}
                    ></ProjectItem>
                ))}
            </div>

        </div>
    );
};

export default ProjectList;
