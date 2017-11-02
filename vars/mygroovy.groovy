#!/usr/bin/env groovy

//import GroupInfo
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

def call(String stage = 'INSTALL'){
 println('shared libary called')
 def Group []group = new Group[2]
 //def Pipeline []pipeline = new Pipeline[2]
 for(int i=0; i<2; i++){
              group[i] = new Group()
 }
 fillPipelineInfo("Multihardware CI", group)
 if (stage == "INSTALL"){
	 for(int j = 0; j<group.size(); j++){
               List<Pipeline>pipelineList = group[j].pipelines
              for(int i =0; i < pipelineList.size(); i++){
                  println("InstallInfo : pipelineName : ${pipelineList[i].pipelineName} installCmd : ${group[j].InstallSteps} ipList : ${pipelineList[i].ipList} ")
                  //Install(pipelineList[i].pipelineName, groupList[j].installCmdList, pipelineList[i].ipList)
              }
          }	
 }else if (stage == "TEST"){
	  for(int j = 0; j<group.size(); j++){
               List<Pipeline>pipelineList = group[j].pipelines
              for(int i =0; i < pipelineList.size(); i++){
                  println("TestRunInfo : TestCmd : ${pipelineList[i].testCmd} testParam : ${pipelineList[i].testParamList} ")
              }
          }
 }else{
	 println("Unknown stage")
 }
}
 
 def fillPipelineInfo(collectionName,Group[] group){
      group[0].groupName = "SMT Functional"
		  group[0].InstallSteps.add("python /a/lib/pepper/command_line/command_line/RunPepper.py --system sushil_smt --role smtcore --run_play --qs_args CURRENT_ARCHIVE_ROOT:/ghostcache2/smtcore-trunk/smtcore")
		  group[0].InstallSteps.add("python /a/lib/pepper/command_line/command_line/RunPepper.py --system sushil_smt --role pyunit --run_play --qs_args CURRENT_ARCHIVE_ROOT:/ghostcache2/smtcore-trunk/smtcore")
		  group[0].InstallSteps.add("python /a/lib/pepper/command_line/command_line/RunPepper.py --system sushil_smt --role pyunit --run_play --qs_args CURRENT_ARCHIVE_ROOT:/ghostcache2/smtcore-trunk/smtcore")
		  def Pipeline[] pipeline = new Pipeline[2]
		  for(int index = 0; index < 2; index++){
		  pipeline[index] = new Pipeline()
		  }
		  pipeline[0].pipelineName = "Functional"
		  pipeline[0].ipList.add("198.18.35.132")
		  pipeline[0].ipList.add("172.24.53.36")
		  pipeline[0].ipList.add("198.18.36.173")
		  pipeline[0].inProgress = "True"
		  pipeline[0].testCmd.add("python testrunner.py")
		  pipeline[0].testCmd.add("python testrunner.py")
		  pipeline[0].testParamList.add("-ts ../smtcore_test/testsuite --include_category=smt --exclude_categoty=in_progress")
		  pipeline[0].testParamList.add("-ts ../smtcore_test/testsuite --include_category=smt --include_categoty=in_progress")
		  group[0] << pipeline[0]
		  pipeline[1].pipelineName = "slow"
		  pipeline[1].ipList.add("172.24.52.39")
		  pipeline[1].ipList.add("172.24.52.47")
		  pipeline[1].ipList.add("172.24.52.18")
		  pipeline[1].inProgress = "False"
		  pipeline[1].testCmd.add("python testrunner.py")
		  pipeline[1].testParamList.add("-ts ../smtcore_test/testsuite --include_category=smt, slow --exclude_categoty=in_progress")
		  group[0] << pipeline[1]
		  group[1].groupName = "XBC Functional"
		  group[1].InstallSteps.add("python /a/lib/pepper/command_line/command_line/RunPepper.py --system sushil_xbc --role smtcore --run_play --qs_args CURRENT_ARCHIVE_ROOT:/ghostcache2/smtcore-trunk/smtcore")
		  group[1].InstallSteps.add("python /a/lib/pepper/command_line/command_line/RunPepper.py --system sushil_xbc --role pyunit --run_play --qs_args CURRENT_ARCHIVE_ROOT:/ghostcache2/smtcore-trunk/smtcore")
		  group[1].InstallSteps.add("python /a/lib/pepper/command_line/command_line/RunPepper.py --system sushil_xbc --role pyunit --run_play --qs_args CURRENT_ARCHIVE_ROOT:/ghostcache2/smtcore-trunk/smtcore")
		  pipeline = new Pipeline[3]
		  for(int index = 0; index < 3; index++){
		  pipeline[index] = new Pipeline()
		  }
		  pipeline[0].pipelineName = "Functional G4"
		  pipeline[0].ipList.add("172.24.52.44")
		  pipeline[0].ipList.add("198.18.35.205")
		  pipeline[0].ipList.add("198.18.49.227")
		  pipeline[0].inProgress = "False"
		  pipeline[0].testCmd.add("python testrunner.py")
		  pipeline[0].testParamList.add("-ts ../smtcore_test/testsuite --include_category=xbc --exclude_categoty=in_progress")
		  group[1] << pipeline[0]
		  pipeline[1].pipelineName = "Functional X7"
		  pipeline[1].ipList.add("172.24.52.51")
		  pipeline[1].ipList.add("198.18.122.61")
		  pipeline[1].ipList.add("172.24.52.35")
		  pipeline[1].inProgress = "False"
		  pipeline[1].testCmd.add("python testrunner.py")
		  pipeline[1].testParamList.add("-ts ../smtcore_test/testsuite --include_category=xbc --exclude_categoty=in_progress")
		  group[1] << pipeline[1]
		  pipeline[2].pipelineName = "slow"
		  pipeline[2].ipList.add("172.24.52.23")
		  pipeline[2].ipList.add("172.24.52.36")
		  pipeline[2].ipList.add("172.24.52.37")
		  pipeline[2].inProgress = "False"
		  pipeline[2].testCmd.add("python testrunner.py")
		  pipeline[2].testParamList.add("-ts ../smtcore_test/testsuite --include_category=xbc, slow --exclude_categoty=in_progress")
		  group[1] << pipeline[2]
 }
