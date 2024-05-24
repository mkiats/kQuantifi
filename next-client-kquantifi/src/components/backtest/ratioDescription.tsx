import 'server-only';

interface RatioDescriptionProps {
	metricHeader: String;
}

const RatioDescription: React.FC<RatioDescriptionProps> = ({ metricHeader }) => {
	return <div className=''>{metricHeader}</div>;
};

export default RatioDescription;
