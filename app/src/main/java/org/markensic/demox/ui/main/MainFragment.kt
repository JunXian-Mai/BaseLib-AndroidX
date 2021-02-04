package org.markensic.demox.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import org.markensic.baselibrary.api.utils.ThreadUtils
import org.markensic.baselibrary.api.utils.XmlParserUtils
import org.markensic.baselibrary.framework.ui.BaseUiView
import org.markensic.baselibrary.global.AppLog
import org.markensic.baselibrary.global.loge
import org.markensic.baselibrary.global.print
import org.markensic.baselibrary.global.tryCatch
import org.markensic.demox.R

class MainFragment : Fragment(), BaseUiView {

  companion object {
    fun newInstance() = MainFragment()
  }

  private lateinit var viewModel: MainViewModel
  private lateinit var rootView: View

  private lateinit var logIoTest: Button
  private lateinit var crashLogTest: Button
  private lateinit var xmlParserTest: Button
  private lateinit var releasableDelegateTest: Button

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    rootView = inflater.inflate(R.layout.main_fragment, container, false)
    return rootView
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

    logIoTest = rootView.findViewById(R.id.log_io_test)
    crashLogTest = rootView.findViewById(R.id.crash_log_test)
    xmlParserTest = rootView.findViewById(R.id.xml_parser_test)
    releasableDelegateTest = rootView.findViewById(R.id.releasable_delegate_test)

    logIoTest.setOnClickListener {
//      Thread {
//        for (t in 1..10000000000) {
//          loge("Thread1 count $t")
//          val sleepTime = 1 * Math.random() + 1
//          Thread.sleep(sleepTime.toLong())
//        }
//      }.start()
//      Thread {
//        for (t in 10000000001..20000000000) {
//          loge("Thread2 count $t")
//          val sleepTime = 1 * Math.random() + 1
//          Thread.sleep(sleepTime.toLong())
//        }
//      }.start()
//      Thread {
//        for (t in 20000000001..30000000000) {
//          loge("Thread3 count $t")
//          val sleepTime = 1 * Math.random() + 1
//          Thread.sleep(sleepTime.toLong())
//        }
//      }.start()

      ThreadUtils.createCommonThreadPool("test-commonPool").execute {
        for (t in 30000000001..40000000000) {
          loge("Thread4 count $t")
          val sleepTime = 1 * Math.random() + 1
          Thread.sleep(sleepTime.toLong())
        }
      }
      ThreadUtils.createCommonThreadPool("test-commonPool").execute {
        for (t in 40000000001..50000000000) {
          loge("Thread5 count $t")
          val sleepTime = 1 * Math.random() + 1
          Thread.sleep(sleepTime.toLong())
        }
      }
      ThreadUtils.createCommonThreadPool("test-commonPool").execute {
        for (t in 50000000001..60000000000) {
          loge("Thread6 count $t")
          val sleepTime = 1 * Math.random() + 1
          Thread.sleep(sleepTime.toLong())
        }
      }
      ThreadUtils.createCommonThreadPool("test-commonPool").execute {
        for (t in 60000000001..70000000000) {
          loge("Thread7 count $t")
          val sleepTime = 1 * Math.random() + 1
          Thread.sleep(sleepTime.toLong())
        }
      }
      ThreadUtils.createCommonThreadPool("test-commonPool").execute {
        for (t in 70000000001..80000000000) {
          loge("Thread8 count $t")
          val sleepTime = 1 * Math.random() + 1
          Thread.sleep(sleepTime.toLong())
        }
      }
    }

    crashLogTest.setOnClickListener {
      val nullString: String? = null
      Log.e(":test", nullString!!)
    }

    xmlParserTest.setOnClickListener {
      testXmlParser()
    }

    releasableDelegateTest.setOnClickListener {
      testReleasableDelegate()
    }

    testTryCatch()

    testTryCatch()
  }

  //region xml解析为json
  fun testXmlParser() {
    val xmlDate = """
            <?xml version="1.0" encoding="UTF-8"?>
            <Transaction>
              <Transaction_Header>
                <tran_response>
                  <QJGZH><![CDATA[1010111681599113654421152]]></QJGZH>
                  <ZQLSH><![CDATA[0200003550]]></ZQLSH>
                  <status><![CDATA[FAIL]]></status>
                  <POS_RSP_CODE><![CDATA[96]]></POS_RSP_CODE>
                </tran_response>
                <transaction_id><![CDATA[POSJHZF08]]></transaction_id>
                <SYS_EVT_TRACE_ID><![CDATA[1010111681599113654421152]]></SYS_EVT_TRACE_ID>
              </Transaction_Header>
              <Transaction_Body>
                <response>
                  <trade_state_desc><![CDATA[0200003550,[TBL_JHZF_TRAN]流水记录不存在]]></trade_state_desc>
                  <bank_type/>
                  <MERCHANT_CODE><![CDATA[105000153110676]]></MERCHANT_CODE>
                  <POSB_TERM_NO><![CDATA[10040255]]></POSB_TERM_NO>
                  <BATCH_NO/>
                  <Rtrvl_Ref_No/>
                  <POS_TRC_NO/>
                  <Acpt_Lnd_Txn_Tm/>
                  <Acpt_Lnd_Txn_Dt/>
                  <OnLn_Py_Txn_Ordr_ID/>
                  <S2P_TRANS_TYPE/>
                  <Pos_Txn_Status><![CDATA[FA]]></Pos_Txn_Status>
                  <Cst_AccNo/>
                  <Ahn_TxnAmt/>
                  <CardNo_Inpt_MtdCd/>
                  <POS_ID><![CDATA[1050001531106760014]]></POS_ID>
                </response>
              </Transaction_Body>
            </Transaction>
            """
    val json = XmlParserUtils.pullTransactionXml(xmlDate)
    AppLog.e("XML", "json -> $json");
  }
  //endregion

  //region 可释放非空对象测试
  fun testReleasableDelegate() {
  }
  //endregion


  fun testTryCatch() {
    tryCatch {
      throw RuntimeException("RuntimeException")
    }.print()
  }
}
