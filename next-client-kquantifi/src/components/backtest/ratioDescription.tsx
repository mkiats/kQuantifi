import 'server-only';

interface RatioDescriptionProps {
	metricHeader: String;
}
export const RatioDescription: React.FC<RatioDescriptionProps> = ({
	metricHeader,
}) => {
	return (
		<div className=''>
			{metricHeader}
			<p>This is a generic description</p>
		</div>
	);
};

interface SummaryDescriptionProps {
	metricHeader: String;
}
export const SummaryDescription: React.FC<SummaryDescriptionProps> = ({
	metricHeader,
}) => {
	return (
		<div className=''>
			{metricHeader}
			<p>This is about portfolio performance. </p>
		</div>
	);
};

interface CagrDescriptionProps {
	metricHeader: String;
}
export const CagrDescription: React.FC<CagrDescriptionProps> = ({
	metricHeader,
}) => {
	return (
		<div className=''>
			{metricHeader}
			<p>This is about compound annual growth rate</p>
		</div>
	);
};

interface DrawdownDescriptionProps {
	metricHeader: String;
}
export const DrawdownDescription: React.FC<DrawdownDescriptionProps> = ({
	metricHeader,
}) => {
	return (
		<div className=''>
			{metricHeader}
			<p>This is about max drawdown. </p>
		</div>
	);
};
