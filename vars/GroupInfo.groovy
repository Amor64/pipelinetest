          import java.io.*;
            class Group implements java.io.Serializable {
            String groupName
            List<String> InstallSteps = []
            List<Pipeline> pipelines = []
            void addPipeline( Pipeline p ) {
                pipelines << p
            }
            Group leftShift( Pipeline p ) {
                addPipeline( p )
                this
            }
            String toString() {
                "$groupName, $InstallSteps $pipelines"
            }
        }
        
     class Pipeline implements java.io.Serializable{
        String pipelineName
        String inProgress
        List<String>ipList = []
        List<String> testCmd = []
        List<String> testParamList = []
        String toString() {
            "($pipelineName $isProgress $testCmd $testParam)"
        }
    }
