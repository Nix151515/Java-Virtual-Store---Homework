package tema;
public interface Visitor
{
	void visit(VideoDepartment videoDep);
	void visit(BookDepartment bookDep);
	void visit(SoftwareDepartment softDep);
	void visit(MusicDepartment musicDep);
}