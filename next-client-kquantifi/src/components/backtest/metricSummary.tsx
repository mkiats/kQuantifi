import 'server-only';

interface MetricSummaryProps {
	metricHeader: String;
}

const MetricSummary: React.FC<MetricSummaryProps> = ({ metricHeader }) => {
	return <div className=''>{metricHeader}</div>;
};

export default MetricSummary;
