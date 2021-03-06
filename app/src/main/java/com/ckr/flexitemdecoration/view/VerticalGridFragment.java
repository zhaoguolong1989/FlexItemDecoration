package com.ckr.flexitemdecoration.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ckr.decoration.DividerGridItemDecoration;
import com.ckr.flexitemdecoration.R;
import com.ckr.flexitemdecoration.adapter.MainAdapter;

import java.util.Arrays;

import butterknife.BindDimen;
import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class VerticalGridFragment extends BaseFragment {
	private static final String TAG = "VerticalGridFragment";
	public static final String STYLE = "style";
	@BindView(R.id.recyclerView)
	RecyclerView recyclerView;
	@BindDimen(R.dimen.size10)
	int padding;
	private MainAdapter mainAdapter;
	public static final int SPAN_COUNT = 2;
	public static final int ORIENTATION = LinearLayoutManager.VERTICAL;
	private DividerGridItemDecoration itemDecoration;
	public boolean[] is_checked = {true, false, false, false, false, false, false, false, false, false, false};
	private boolean isInit = false;
	private boolean isShowOtherStyle;

	public static VerticalGridFragment newInstance(boolean b) {
		Bundle args = new Bundle();
		args.putBoolean(STYLE, b);
		VerticalGridFragment fragment = new VerticalGridFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		Bundle arguments = getArguments();
		if (arguments != null) {
			isShowOtherStyle = arguments.getBoolean(STYLE, false);
		}
	}

	@Override
	protected int getContentLayoutId() {
		return R.layout.fragment_main;
	}

	@Override
	protected void init() {
		isInit = true;
		setItemDecoration();
		recyclerView.setLayoutManager(new GridLayoutManager(getContext(), SPAN_COUNT, ORIENTATION, false));
		recyclerView.setPadding(padding, padding, padding, padding);
		mainAdapter = new MainAdapter(getContext());
		recyclerView.setAdapter(mainAdapter);
	}

	private void setItemDecoration() {
		if (!isInit) {
			return;
		}
		if (itemDecoration != null) {
			recyclerView.removeItemDecoration(itemDecoration);
		}
		DividerGridItemDecoration.Builder builder = new DividerGridItemDecoration.Builder(getContext(), ORIENTATION, SPAN_COUNT);
//        DividerGridItemDecoration.Builder builder = new DividerGridItemDecoration.Builder(getContext(), SPAN_COUNT);
		builder.setShowOtherStyle(isShowOtherStyle)
				.setDivider(R.drawable.bg_divider_list);
		if (is_checked[0]) {
		} else {
			builder.removeHeaderDivider(is_checked[1])
					.removeFooterDivider(is_checked[2])
					.removeLeftDivider(is_checked[3])
					.removeRightDivider(is_checked[4])
			;
		}
		if (is_checked[5]) {
			builder.subDivider(1, 4)
					.setSubDividerHeight(24)
					.setSubDividerWidth(24)
					.setSubDividerDrawable(R.drawable.bg_divider_offset_grid);
		}
		if (is_checked[6]) {
			builder.redrawDivider(2)
					.redrawDividerHeight(30)
					.redrawDividerDrawable(R.drawable.bg_divider_redraw_grid);
		}
		if (is_checked[7]) {
			builder.redrawHeaderDivider()
					.redrawHeaderDividerHeight(40)
					.redrawHeaderDividerDrawable(R.drawable.bg_divider_offset_grid);
		}
		if (is_checked[8]) {
			builder.redrawFooterDivider()
					.redrawFooterDividerHeight(40)
					.redrawFooterDividerDrawable(R.drawable.bg_divider_offset_grid);
		}
		if (is_checked[9]) {
			builder.redrawLeftDivider().
					redrawLeftDividerWidth(40)
					.redrawLeftDividerDrawable(R.drawable.bg_divider_list);
		}
		if (is_checked[10]) {
			builder.redrawRightDivider()
					.redrawRightDividerWidth(40)
					.redrawRightDividerDrawable(R.drawable.bg_divider_list);
		}
		itemDecoration = builder.build();
		recyclerView.addItemDecoration(itemDecoration);
	   /* itemDecoration = new DividerGridItemDecoration(getContext(), BaseItemDecoration.HORIZONTAL,SPAN_COUNT);
        itemDecoration.setDivider(R.drawable.bg_divider_list);
        if (is_checked[0]) {
        } else {
            itemDecoration.removeHeaderDivider(is_checked[1])
                    .removeFooterDivider(is_checked[2])
                    .removeLeftDivider(is_checked[3])
                    .removeRightDivider(is_checked[4])
            ;
        }
        recyclerView.addItemDecoration(itemDecoration);*/
	}

	@Override
	public void refreshFragment(boolean... params) {
		Log.d(TAG, "refreshFragment: params:" + Arrays.toString(params));
		if (!Arrays.equals(params, is_checked)) {
			System.arraycopy(params, 0, is_checked, 0, params.length);
			Log.d(TAG, "refreshFragment: is_checked:" + Arrays.toString(is_checked));
			setItemDecoration();
		}
	}
}
