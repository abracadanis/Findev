import { ProjectSo } from '../../openapi';
import ProjectItem from './ProjectItem';

interface ProjectProps {
    project: ProjectSo[];
}

const ProjectList = (props: ProjectProps) => {
    const projectItems = () => {};
    return (
        <div className='d-flex flex-row'>
            {props.project.map((project) => (
                <ProjectItem
                    key={project.title}
                    {...project}
                ></ProjectItem>
            ))}
        </div>
    );
};

export default ProjectList;
